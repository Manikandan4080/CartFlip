package com.example.CartFlip.Controller;

import com.example.CartFlip.Dtos.RequestDto.CustomerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CustomerResponseDto;
import com.example.CartFlip.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity<>(customerRequestDto, HttpStatus.CREATED);

    }

    // customers who have ordered at least 'k' orders
    @GetMapping("/customer-k-order")
    public ResponseEntity customerKOrders(@RequestParam int k){
        List<CustomerResponseDto> customerResponseDtoList = customerService.customerKOrders(k);
        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.OK);
    }

}
