package com.ecommerce.nexus.repositories;

import com.ecommerce.nexus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
