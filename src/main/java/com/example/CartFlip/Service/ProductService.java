package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.ProductRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.ProductResponseDto;
import com.example.CartFlip.Enum.Category;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addSeller(ProductRequestDto productRequestDto);

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);

    List<ProductResponseDto> getProductOfACategory(String category);

    List<ProductResponseDto> getProductOfACategoryPriceGreater(String category, int minPrice);

    List<ProductResponseDto> getKCheapestProductACategory(String category, int k);

    List<ProductResponseDto> getKCostliestProductACategory(String category, int k);

    List<ProductResponseDto> getAllProductOfASeller(String emailId);

    List<ProductResponseDto> getAllOutOfProductOfACategory(String category);
}
