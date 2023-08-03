package com.example.CartFlip.Dtos.RequestDto;

import com.example.CartFlip.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String sellerEmailId;// from seller

    String name;

    Integer price;

    Category category;

    Integer quantity;

}

