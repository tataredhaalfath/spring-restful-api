package spring.belajarspringrestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.belajarspringrestfulapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {

}
