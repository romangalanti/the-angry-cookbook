package com.libertymutual.goforcode.theangrycookbook.api;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.theangrycookbook.repositories.IngredientRepository;
import com.libertymutual.goforcode.theangrycookbook.repositories.InstructionRepository;
import com.libertymutual.goforcode.theangrycookbook.repositories.RecipeRepository;

public class RecipeApiControllerTests {
	
	private RecipeRepository recipeRepo; 
	private IngredientRepository ingredientRepo; 
	private InstructionRepository instructionRepo; 
	private RecipeApiController controller; 

	
	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class); 
		ingredientRepo = mock(IngredientRepository.class); 
		instructionRepo = mock(InstructionRepository.class); 
		
		controller = new RecipeApiController(ingredientRepo, instructionRepo, recipeRepo); 
	}
	
	@Test
	public void test_getAll_returns_all_recipes_returned_by_the_repo() {
		
	}
	
	
}
