package com.example.CartFlip.Service.ServiceImpl;

import com.example.CartFlip.Dtos.RequestDto.CardRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CardResponseDto;
import com.example.CartFlip.Enum.CardType;
import com.example.CartFlip.Exceptions.CustomerNotFoundException;
import com.example.CartFlip.Model.Card;
import com.example.CartFlip.Model.Customer;
import com.example.CartFlip.Repository.CardRepository;
import com.example.CartFlip.Repository.CustomerRepository;
import com.example.CartFlip.Service.CardService;
import com.example.CartFlip.Transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Invalid Email Id");
        }
        //dto to entity

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);


        Customer savedCustomer = customerRepository.save(customer);//save both customer and card


        //prepare response dto

        CardResponseDto cardResponseDto = CardTransformer.CardToCardResponseDto(card);

        return cardResponseDto;
    }

    @Override
    public List<String> getMaxCardType() {

        List<Card> cards = cardRepository.findAll();
        Map<CardType,Integer> hm = new HashMap<>();
        int max = 1;

        for (Card card : cards){
            if(hm.containsKey(card.getCardType())){
                int oldVal = hm.get(card.getCardType());
                hm.put(card.getCardType(),oldVal+1);
                if(max < hm.get(card.getCardType()) ){
                    max = hm.get(card.getCardType());
                }
            }
            else{
                hm.put(card.getCardType(),1);
            }
        }
        System.out.println(hm);
        List<String> cardTypeList = new ArrayList<>();
        for(CardType cardType : hm.keySet()){
            if(hm.get(cardType) == max){
                cardTypeList.add(String.valueOf(cardType));
            }
        }
        return cardTypeList;

    }

    @Override
    public List<String> getMinCardType() {
        List<Card> cards = cardRepository.findAll();
        Map<CardType ,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        boolean flag = false;

        for (Card card : cards){
            if(hm.containsKey(card.getCardType())){
                flag = true;
                int oldVal = hm.get(card.getCardType());
                hm.put(card.getCardType(),oldVal+1);
                if(min > hm.get(card.getCardType()) ){
                    min = hm.get(card.getCardType());
                }
            }
            else{
                hm.put(card.getCardType(),1);

            }
        }
        System.out.println(hm);
        List<String> cardTypeList = new ArrayList<>();
        for(CardType cardType : hm.keySet()){
            if (flag == false){
                cardTypeList.add(String.valueOf(cardType));
            }
            else if(flag == true && hm.get(cardType) == min){
                cardTypeList.add(String.valueOf(cardType));
            }
        }
        return cardTypeList;
    }
}
