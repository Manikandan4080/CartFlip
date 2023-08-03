package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.ResponseDto.CartResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.ItemResponseDto;
import com.example.CartFlip.Model.Cart;
import com.example.CartFlip.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart) {

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for(Item item : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }


        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemResponseDtos)
                .build();



    }
}
