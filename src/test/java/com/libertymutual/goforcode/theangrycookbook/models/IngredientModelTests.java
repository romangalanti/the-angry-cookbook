package com.libertymutual.goforcode.theangrycookbook.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class IngredientModelTests {
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Ingredient ingredient = new Ingredient("Will", "Ferrell", 1);

		// Assert
		assertThat(ingredient.getNameOfFoodItem()).isEqualTo("Will");
		assertThat(ingredient.getUnitOfMeasurement()).isEqualTo("Ferrell");
		assertThat(ingredient.getQuantity()).isEqualTo(1);
	}
	
	@Test
	public void test_required_argument_constructor() {
		// Act
		Ingredient ingredient = new Ingredient("Will", "Ferrell");

		// Assert
		assertThat(ingredient.getNameOfFoodItem()).isEqualTo("Will");
		assertThat(ingredient.getUnitOfMeasurement()).isEqualTo("Ferrell");
	}
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(Ingredient.class);
	}

}
