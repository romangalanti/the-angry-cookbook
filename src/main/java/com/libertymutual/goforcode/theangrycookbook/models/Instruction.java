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
public class Instruction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=500)
	private String instruction;
	
	@Column(nullable=true, length=255)
	private String url;
	
	@ManyToOne
	private Recipe recipe;
	
	public Instruction() {}
	
	public Instruction(String instruction) {
		this.instruction = instruction;
	}
	
	public Instruction(String instruction, String url) {
		this.instruction = instruction;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
