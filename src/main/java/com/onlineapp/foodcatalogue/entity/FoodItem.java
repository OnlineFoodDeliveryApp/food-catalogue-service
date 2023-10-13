package com.onlineapp.foodcatalogue.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String itemName;

	private String itemDescription;

	private boolean isVeg;

	private Long price;

	private Integer restaurantId;

	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer quantity;

}
