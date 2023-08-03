package com.example.CartFlip.Service.ServiceImpl;

import com.example.CartFlip.Dtos.RequestDto.CheckoutRequestDto;
import com.example.CartFlip.Dtos.RequestDto.ItemRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.CartResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.OrderResponseDto;
import com.example.CartFlip.Exceptions.CustomerNotFoundException;
import com.example.CartFlip.Exceptions.EmptyCartException;
import com.example.CartFlip.Exceptions.InsufficientQuantityException;
import com.example.CartFlip.Exceptions.InvalidCardException;
import com.example.CartFlip.Model.*;
import com.example.CartFlip.Repository.*;
import com.example.CartFlip.Service.CartService;
import com.example.CartFlip.Service.OrderService;
import com.example.CartFlip.Transformers.CartTransformer;
import com.example.CartFlip.Transformers.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public CartResponseDto addCart(Item item, ItemRequestDto itemRequestDto) {


        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();

        cart.setCartTotal(cart.getCartTotal()+ item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);


        Cart savedCart = cartRepository.save(cart);
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItems().add(savedItem);

        //prepare request dto

        CartResponseDto cartResponseDto = CartTransformer.CartToCartResponseDto(savedCart);
        return cartResponseDto;
    }

    public OrderResponseDto checkoutCart(CheckoutRequestDto checkoutRequestDto){

        Customer customer = customerRepository.findByEmailId(checkoutRequestDto.getCustomerEmailId());
        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }

        Card card = cardRepository.findByCardNo(checkoutRequestDto.getCardNo());
        Date date = new Date();
        if (card == null || card.getCvv() != checkoutRequestDto.getCvv()){//change from after to before || date.before(card.getValidTill())
            throw new InvalidCardException("Card is expired");
        }

        Cart cart = customer.getCart();
        if (cart.getItems().size() == 0){
            throw new EmptyCartException("Cart is empty!");
        }


        try {
            OrderEntity order = orderService.placeOrder(cart,card);
            resetCart(cart);
            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            //send email
            String text = "Congrats ! "+ customer.getName()+ " App apna poora cat khali kr rhe hain";
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("akashlkog22@gmail.com");
            message.setTo(customer.getEmailId());
            message.setSubject("Cart Empty!!!!");
            message.setText(text);
            emailSender.send(message);



            return OrderTransformer.OrderToOrderResponseDto(savedOrder);

        }catch (InsufficientQuantityException e){
            throw e;
        }

    }

    private void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }
}
