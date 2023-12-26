package spring.belajarspringrestfulapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.belajarspringrestfulapi.entity.Contact;
import spring.belajarspringrestfulapi.entity.User;

public interface ContactRepository extends JpaRepository<Contact, String> {
    Optional<Contact> findFirstByUserAndId(User user, String id);
}
