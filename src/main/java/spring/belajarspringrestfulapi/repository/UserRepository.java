package spring.belajarspringrestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.belajarspringrestfulapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
