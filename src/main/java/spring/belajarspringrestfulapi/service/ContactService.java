package spring.belajarspringrestfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.belajarspringrestfulapi.entity.Contact;
import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.ContactResponse;
import spring.belajarspringrestfulapi.model.CreateContactRequest;
import spring.belajarspringrestfulapi.repository.ContactRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);
        contactRepository.save(contact);

        return ContactResponse.builder().id(contact.getId()).firstName(contact.getFirstName())
                .lastName(contact.getLastName()).email(contact.getEmail()).phone(contact.getPhone()).build();
    }
}
