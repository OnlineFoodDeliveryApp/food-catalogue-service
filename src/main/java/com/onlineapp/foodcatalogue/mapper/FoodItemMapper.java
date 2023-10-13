package com.onlineapp.foodcatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.onlineapp.foodcatalogue.dto.FoodItemDTO;
import com.onlineapp.foodcatalogue.entity.FoodItem;

@Mapper
public interface FoodItemMapper {
	
	FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
	
	FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);

    FoodItemDTO mapFoodItemToFoodItemDto(FoodItem foodItem);


}
