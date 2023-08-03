package com.example.CartFlip.Dtos.RequestDto;

import com.example.CartFlip.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {


    String emailId;

    String cardNo;

    Integer cvv;

    CardType cardType;

    Date validTill;


}