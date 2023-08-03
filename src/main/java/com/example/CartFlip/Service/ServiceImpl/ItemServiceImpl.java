package com.example.CartFlip.Service.ServiceImpl;

import com.example.CartFlip.Dtos.RequestDto.ItemRequestDto;
import com.example.CartFlip.Exceptions.CustomerNotFoundException;
import com.example.CartFlip.Exceptions.InsufficientQuantityException;
import com.example.CartFlip.Exceptions.ProductNotFoundException;
import com.example.CartFlip.Exceptions.ProductOutOfStockException;
import com.example.CartFlip.Model.Customer;
import com.example.CartFlip.Model.Item;
import com.example.CartFlip.Model.Product;
import com.example.CartFlip.Repository.CustomerRepository;
import com.example.CartFlip.Repository.ProductRepository;
import com.example.CartFlip.Service.ItemService;
import com.example.CartFlip.Transformers.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product does not exist");
        }


        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());

        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }

        Product product = productOptional.get();

        if(product.getQuantity() == 0){
            throw  new ProductOutOfStockException("Product is out of stock bro!");
        }

        if(product.getQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());

//        product.getItems().add(item);

        return item;

    }
}
