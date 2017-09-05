package com.libertymutual.goforcode.theangrycookbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id")
@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=75)
	private String nameOfFoodItem;
	
	@Column(nullable=false, length=75)
	private String unitOfMeasurement;
	
	@Column(nullable=true)
	private double quantity;
	
	@ManyToOne
	private Recipe recipe;
	
	public Ingredient() {}
	
	public Ingredient(String nameOfFoodItem, String unitOfMeasurement, double quantity) {
		this.nameOfFoodItem = nameOfFoodItem;
		this.unitOfMeasurement = unitOfMeasurement;
		this.quantity = quantity;
	}
	
	public Ingredient(String nameOfFoodItem, String unitOfMeasurement) {
		this.nameOfFoodItem = nameOfFoodItem;
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOfFoodItem() {
		return nameOfFoodItem;
	}

	public void setNameOfFoodItem(String nameOfFoodItem) {
		this.nameOfFoodItem = nameOfFoodItem;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
