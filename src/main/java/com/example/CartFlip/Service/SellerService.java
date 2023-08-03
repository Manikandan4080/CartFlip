package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.SellerRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.ProductResponseDto;
import com.example.CartFlip.Dtos.ResponseDto.SellerResponseDto;
import com.example.CartFlip.Enum.Category;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);


    String updateMobNo(String emailId, String newMobNo);

    List<SellerResponseDto> getByParticularCategory(Category category);

    List<ProductResponseDto> getAllProducts(String emailId);

    List<SellerResponseDto> getSellersWithHighestNumberOfProducts();

    List<SellerResponseDto> getSellersWithLowestNumberOfProducts();

    List<SellerResponseDto> getSellersWithCostliestProducts();

    List<SellerResponseDto> getSellersWithCheapestProducts();
}