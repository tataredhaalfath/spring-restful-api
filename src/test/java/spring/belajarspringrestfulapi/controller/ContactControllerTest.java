package spring.belajarspringrestfulapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.belajarspringrestfulapi.entity.Contact;
import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.ContactResponse;
import spring.belajarspringrestfulapi.model.CreateContactRequest;
import spring.belajarspringrestfulapi.model.UpdateContactRequest;
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
    void createContactSuccess() throws Exception {
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

    @Test
    void createContactNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contact/123123123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isNotFound())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {

                            });
                    assertNotNull(response.getErrors());
                });
    }

    @Test
    void getContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElseThrow();

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("tata redha");
        contact.setLastName("al fath");
        contact.setEmail("redha@mail.com");
        contact.setPhone("085325224829");
        contact.setUser(user);
        contactRepository.save(contact);

        mockMvc.perform(
                get("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<ContactResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(contact.getId(), response.getData().getId());
                    assertEquals(contact.getFirstName(), response.getData().getFirstName());
                    assertEquals(contact.getLastName(), response.getData().getLastName());
                    assertEquals(contact.getEmail(), response.getData().getEmail());
                    assertEquals(contact.getPhone(), response.getData().getPhone());
                });
    }

    @Test
    void updateContactBadRequest() throws Exception {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("");
        request.setEmail("salah");

        mockMvc.perform(
                put("/api/contact/1234")
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
    void updateContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElseThrow();

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("tata redha");
        contact.setLastName("al fath");
        contact.setEmail("redha@mail.com");
        contact.setPhone("085325224829");
        contact.setUser(user);
        contactRepository.save(contact);

        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("budi");
        request.setLastName("nugraha");
        request.setEmail("budi@mail.com");
        request.setPhone("085325123123");

        mockMvc.perform(
                put("/api/contacts/" + contact.getId())
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
                    assertEquals(request.getFirstName(), response.getData().getFirstName());
                    assertEquals(request.getLastName(), response.getData().getLastName());
                    assertEquals(request.getEmail(), response.getData().getEmail());
                    assertEquals(request.getPhone(), response.getData().getPhone());

                    assertTrue(contactRepository.existsById(response.getData().getId()));
                });
    }

    @Test
    void deleteContactNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/contact/123123123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isNotFound())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {

                            });
                    assertNotNull(response.getErrors());
                });
    }

    @Test
    void deleteContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElseThrow();

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("tata redha");
        contact.setLastName("al fath");
        contact.setEmail("redha@mail.com");
        contact.setPhone("085325224829");
        contact.setUser(user);
        contactRepository.save(contact);

        mockMvc.perform(
                delete("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals("OK", response.getData());
                });
    }

    @Test
    void searchNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(0, response.getData().size());
                    assertEquals(0, response.getPaging().getTotalPage());
                    assertEquals(0, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });
    }

    @Test
    void searchSuccess() throws Exception {
        User user = userRepository.findById("test").orElseThrow();

        for (int i = 0; i < 100; i++) {
            Contact contact = new Contact();
            contact.setId(UUID.randomUUID().toString());
            contact.setFirstName("tata redha" + i);
            contact.setLastName("al fath");
            contact.setEmail("redha@mail.com");
            contact.setPhone("085325224829");
            contact.setUser(user);
            contactRepository.save(contact);
        }

        // By FirstName
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "tata redha")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(10, response.getData().size());
                    assertEquals(10, response.getPaging().getTotalPage());
                    assertEquals(0, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });

        // By Last Name
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "al fath")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(10, response.getData().size());
                    assertEquals(10, response.getPaging().getTotalPage());
                    assertEquals(0, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });

        // By Email
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("email", "redha@mail.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(10, response.getData().size());
                    assertEquals(10, response.getPaging().getTotalPage());
                    assertEquals(0, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });

        // By Phone
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("phone", "24829")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(10, response.getData().size());
                    assertEquals(10, response.getPaging().getTotalPage());
                    assertEquals(0, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });

        // Kelebihan page
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("phone", "24829")
                        .queryParam("page", "1000")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test"))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<List<ContactResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {

                            });
                    assertNull(response.getErrors());
                    assertEquals(0, response.getData().size());
                    assertEquals(10, response.getPaging().getTotalPage());
                    assertEquals(1000, response.getPaging().getCurrentPage());
                    assertEquals(10, response.getPaging().getSize());
                });

    }
}
