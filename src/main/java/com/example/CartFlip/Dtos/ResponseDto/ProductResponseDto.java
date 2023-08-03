package com.example.CartFlip.Dtos.ResponseDto;

import com.example.CartFlip.Enum.Category;
import com.example.CartFlip.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    Category category;

    int price;

    ProductStatus productStatus;

}