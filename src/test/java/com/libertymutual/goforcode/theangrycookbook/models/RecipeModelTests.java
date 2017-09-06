package com.libertymutual.goforcode.theangrycookbook.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class RecipeModelTests {
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Recipe recipe = new Recipe("Will", "Ferrell", 1, 2);

		// Assert
		assertThat(recipe.getTitle()).isEqualTo("Will");
		assertThat(recipe.getDescription()).isEqualTo("Ferrell");
		assertThat(recipe.getNumberOfMinutes()).isEqualTo(1);
		assertThat(recipe.getUnitOfMeasurement()).isEqualTo(2);
	}
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(Recipe.class);
	}

}
