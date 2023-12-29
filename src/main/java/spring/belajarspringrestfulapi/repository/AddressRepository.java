package spring.belajarspringrestfulapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.belajarspringrestfulapi.entity.Address;
import spring.belajarspringrestfulapi.entity.Contact;

public interface AddressRepository extends JpaRepository<Address, String> {
    Optional<Address> findFirstByContactAndId(Contact contact, String id);

    List<Address> findAllByContact(Contact contact);
}
