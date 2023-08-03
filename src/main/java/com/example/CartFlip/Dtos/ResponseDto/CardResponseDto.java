package com.example.CartFlip.Dtos.ResponseDto;

import com.example.CartFlip.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String customerName;

    String cardNo;

    CardType cardType;

}
