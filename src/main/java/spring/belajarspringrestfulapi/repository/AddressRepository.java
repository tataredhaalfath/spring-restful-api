package spring.belajarspringrestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.belajarspringrestfulapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
