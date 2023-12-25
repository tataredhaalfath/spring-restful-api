package spring.belajarspringrestfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.LoginUserRequest;
import spring.belajarspringrestfulapi.model.TokenResponse;
import spring.belajarspringrestfulapi.repository.UserRepository;
import spring.belajarspringrestfulapi.security.BCrypt;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong!"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            // sukses
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder().token(user.getToken()).expiredAt(user.getTokenExpiredAt()).build();
        } else {
            // gagal
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong!");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() * (1000 * 60 * 24 * 30);
    }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }
}
