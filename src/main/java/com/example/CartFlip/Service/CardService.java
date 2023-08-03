package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.CardRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CardResponseDto;

import java.util.List;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto);

    List<String> getMaxCardType();

    List<String> getMinCardType();
}