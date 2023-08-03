package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.RequestDto.CustomerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CustomerResponseDto;
import com.example.CartFlip.Model.Customer;

public class CustomerTransformer {


    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }

    public  static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }
}
