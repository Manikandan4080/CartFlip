package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.OrderRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.OrderResponseDto;
import com.example.CartFlip.Model.Card;
import com.example.CartFlip.Model.Cart;
import com.example.CartFlip.Model.OrderEntity;

import java.util.List;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);

    public OrderEntity placeOrder(Cart cart , Card card);

    List<OrderResponseDto> getTopKOrdersMaxOrderValue(int k);

    List<OrderResponseDto> getOrderOfACustomer(String emailId);

    List<OrderResponseDto> getTopKOrderOfACustomer(String emailId, int k);

    List<OrderResponseDto> getKRecentlyOrderCustomer(String emailId, int k);
}
