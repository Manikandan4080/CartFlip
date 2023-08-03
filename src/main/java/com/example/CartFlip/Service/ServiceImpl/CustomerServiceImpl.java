package com.example.CartFlip.Service.ServiceImpl;

import com.example.CartFlip.Dtos.RequestDto.CustomerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CustomerResponseDto;
import com.example.CartFlip.Model.Cart;
import com.example.CartFlip.Model.Customer;
import com.example.CartFlip.Repository.CustomerRepository;
import com.example.CartFlip.Service.CustomerService;
import com.example.CartFlip.Transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //dto to entity

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        //from cart transformer
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        customerRepository.save(customer);//save both parent and child  i.e. customer and cart

        //prepare response dto

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> customerKOrders(int k) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getOrders().size() == k){
                customerResponseDtoList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
            }
        }
        return customerResponseDtoList;
    }
}
