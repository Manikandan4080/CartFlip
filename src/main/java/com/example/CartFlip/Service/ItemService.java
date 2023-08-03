package com.example.CartFlip.Service;

import com.example.CartFlip.Dtos.RequestDto.ItemRequestDto;
import com.example.CartFlip.Model.Item;

public interface ItemService {


    Item createItem(ItemRequestDto itemRequestDto);
}