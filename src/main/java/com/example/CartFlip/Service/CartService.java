package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.CheckoutRequestDto;
import com.example.CartFlip.Dtos.RequestDto.ItemRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CartResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.OrderResponseDto;
import com.example.CartFlip.Model.Item;

public interface CartService {

    CartResponseDto addCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkoutCart(CheckoutRequestDto checkoutRequestDto);
}