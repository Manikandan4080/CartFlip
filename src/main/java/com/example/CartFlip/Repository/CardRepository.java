package com.example.CartFlip.Repository;

import com.example.CartFlip.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {

    Card findByCardNo(String cardNo);

}