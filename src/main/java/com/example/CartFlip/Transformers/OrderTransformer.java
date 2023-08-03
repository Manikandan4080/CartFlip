package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.ResponseDto.ItemResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.OrderResponseDto;
import com.example.CartFlip.Model.Customer;
import com.example.CartFlip.Model.Item;
import com.example.CartFlip.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity OrderRequestDtoToOrder(Item item, Customer customer){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : orderEntity.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtos)
                .build();
    }
}
