package com.example.CartFlip.Repository;

import com.example.CartFlip.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByEmailId(String emailId);

}
