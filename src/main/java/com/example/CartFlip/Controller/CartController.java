package com.example.CartFlip.Controller;

import com.example.CartFlip.Dtos.RequestDto.CheckoutRequestDto;
import com.example.CartFlip.Dtos.RequestDto.ItemRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CartResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.OrderResponseDto;
import com.example.CartFlip.Model.Item;
import com.example.CartFlip.Service.CartService;
import com.example.CartFlip.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try {
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addCart(item,itemRequestDto);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")// correct the bug
    public ResponseEntity checkoutCart(@RequestBody CheckoutRequestDto checkoutRequestDto){

        try {
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
