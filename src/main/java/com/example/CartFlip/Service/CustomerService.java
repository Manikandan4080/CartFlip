package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.CustomerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> customerKOrders(int k);
}
