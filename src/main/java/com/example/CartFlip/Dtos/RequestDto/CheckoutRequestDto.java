package com.example.CartFlip.Dtos.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutRequestDto {

    String customerEmailId;

    String cardNo;

    int cvv;


}