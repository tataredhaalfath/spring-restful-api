package spring.belajarspringrestfulapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.ContactResponse;
import spring.belajarspringrestfulapi.model.CreateContactRequest;
import spring.belajarspringrestfulapi.model.WebResponse;
import spring.belajarspringrestfulapi.repository.ContactRepository;
import spring.belajarspringrestfulapi.repository.UserRepository;
import spring.belajarspringrestfulapi.security.BCrypt;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        userRepository.save(user);
    }

    @Test
    void createContactBadRequest() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("");
        request.setEmail("salah");

        mockMvc.perform(
                post("/api/contact")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isBadRequest())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {

                            });
                    assertNotNull(response.getErrors());
                });
    }

    @Test
    void createContactSuccesst() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("tata redha");
        request.setLastName("al fath");
        request.setEmail("redha@mail.com");
        request.setPhone("085325224829");

        mockMvc.perform(
                post("/api/contact")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<ContactResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals("tata redha", response.getData().getFirstName());
                    assertEquals("al fath", response.getData().getLastName());
                    assertEquals("redha@mail.com", response.getData().getEmail());
                    assertEquals("085325224829", response.getData().getPhone());

                    assertTrue(contactRepository.existsById(response.getData().getId()));
                });
    }
}
