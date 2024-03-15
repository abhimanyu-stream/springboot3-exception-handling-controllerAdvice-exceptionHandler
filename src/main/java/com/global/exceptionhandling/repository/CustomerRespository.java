package com.global.exceptionhandling.repository;

import com.global.exceptionhandling.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer, Long> {


    Customer findByEmail(String email);
}
