package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.ResponseDto.ItemResponseDto;
import com.example.CartFlip.Model.Item;

public class ItemTransformer {


    public static Item ItemRequestDtoToItem(int requiredQuantity) {


        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();

    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();

    }
}
