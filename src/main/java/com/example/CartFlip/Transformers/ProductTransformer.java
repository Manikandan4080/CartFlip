package com.example.CartFlip.Transformers;

import com.example.CartFlip.Dtos.RequestDto.ProductRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.ProductResponseDto;
import com.example.CartFlip.Enum.ProductStatus;
import com.example.CartFlip.Model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return  Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
