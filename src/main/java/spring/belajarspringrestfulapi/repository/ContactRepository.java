package spring.belajarspringrestfulapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import spring.belajarspringrestfulapi.entity.Contact;
import spring.belajarspringrestfulapi.entity.User;

public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {
    Optional<Contact> findFirstByUserAndId(User user, String id);
}
