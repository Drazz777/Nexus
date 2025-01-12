package com.ecommerce.nexus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nexus.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}