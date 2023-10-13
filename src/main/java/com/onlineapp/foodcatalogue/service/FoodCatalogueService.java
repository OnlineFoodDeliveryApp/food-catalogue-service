package com.onlineapp.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onlineapp.foodcatalogue.dto.FoodCataloguePage;
import com.onlineapp.foodcatalogue.dto.FoodItemDTO;
import com.onlineapp.foodcatalogue.dto.Restaurant;
import com.onlineapp.foodcatalogue.entity.FoodItem;
import com.onlineapp.foodcatalogue.mapper.FoodItemMapper;
import com.onlineapp.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {
	
	@Autowired
	FoodItemRepo foodItemRepo;

	@Autowired
	RestTemplate restTemplate;
	
	public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItemSaved = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
		return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemSaved);
	}
	
	public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItemList =  fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
       return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }


}
