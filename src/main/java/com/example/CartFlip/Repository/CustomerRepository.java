package com.example.CartFlip.Repository;

import com.example.CartFlip.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmailId(String emailId);
}