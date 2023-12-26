package spring.belajarspringrestfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.ContactResponse;
import spring.belajarspringrestfulapi.model.CreateContactRequest;
import spring.belajarspringrestfulapi.model.WebResponse;
import spring.belajarspringrestfulapi.service.ContactService;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping(path = "/api/contact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
        ContactResponse contactResponse = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }
}
