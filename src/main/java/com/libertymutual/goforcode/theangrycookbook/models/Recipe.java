package com.libertymutual.goforcode.theangrycookbook.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property="id")
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=75)
	private String title;
	
	@Column(nullable=false, length=255)
	private String description;
	
	@Column(nullable=false)
	private double numberOfMinutes;
	
	@Column(nullable=true, length=255)
	private String url;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
	private List<Instruction> instructions;
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
	private List<Ingredient> ingredients;
	
	public Recipe() {}
	
	public Recipe(String title, String description, double numberOfMinutes) {
		this.title = title;
		this.description = description;
		this.numberOfMinutes = numberOfMinutes;
	}
	
	public Recipe(String title, String description, double numberOfMinutes, String url) {
		this.title = title;
		this.description = description;
		this.numberOfMinutes = numberOfMinutes;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getNumberOfMinutes() {
		return numberOfMinutes;
	}

	public void setNumberOfMinutes(double numberOfMinutes) {
		this.numberOfMinutes = numberOfMinutes;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
