package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.RequestDto.SellerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.SellerResponseDto;
import com.example.CartFlip.Model.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }


}