package com.libertymutual.goforcode.theangrycookbook.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.theangrycookbook.models.Ingredient;
import com.libertymutual.goforcode.theangrycookbook.models.Instruction;
import com.libertymutual.goforcode.theangrycookbook.models.Recipe;
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
		//arrange
		ArrayList<Recipe> recipes = new ArrayList<Recipe>(); 
		recipes.add(new Recipe()); 
		recipes.add(new Recipe()); 
		
		when(recipeRepo.findAll()).thenReturn(recipes); 
		
		//act
		List<Recipe> actual = controller.getAll(); 
		
		//assert
		assertThat(actual.size()).isEqualTo(2); 
		assertThat(actual.get(0)).isSameAs(recipes.get(0)); 
		verify(recipeRepo).findAll(); 		
		
	}
	
	@Test
	public void test_getOne_returns_recipe_returned_from_repo() throws StuffNotFoundException {
		//arrange
		Recipe recipe = new Recipe(); 
		when(recipeRepo.findOne(9L)).thenReturn(recipe); 
		
		//act
		Recipe actual = controller.getOne(9L); 
		
		//assert
		assertThat(actual).isSameAs(recipe);
		verify(recipeRepo).findOne(9L); 
	}
	
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_movie_returned_from_repo() {
		try {
			controller.getOne(4L); 
			fail("The controller did not throw the StuffNotFoundException."); 
		} catch(StuffNotFoundException snfe) {
			
		}
	}
	
	
	@Test
	public void test_delete_returns_recipe_deleted_when_found() {
		//arrange
		Recipe recipe = new Recipe();
		ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
		ingredient.add(new Ingredient());
		ArrayList<Instruction> instruction = new ArrayList<Instruction>();
		instruction.add(new Instruction());
		recipe.setIngredients(ingredient);
		recipe.setInstructions(instruction);

		when(recipeRepo.findOne(2L)).thenReturn(recipe);
		
		//act
		Recipe actual = controller.delete(2L); 		
		
		//assert
		assertThat(recipe).isSameAs(actual); 
		verify(recipeRepo).delete(2L);
		verify(recipeRepo).findOne(2L); 
	}
	
	@Test
	public void test_recipe_is_created_when_create_is_called() {
		//arrange
		Recipe recipe = new Recipe(); 
		when(recipeRepo.save(recipe)).thenReturn(recipe); 
		
		//act
		Recipe actual = controller.create(recipe); 
		
		//assert
		assertThat(recipe).isSameAs(actual); 
		verify(recipeRepo).save(recipe); 
	}
	
	@Test
	public void test_recipe_is_saved_when_updated_by_id() {
		//arrange
		Recipe recipe = new Recipe(); 
		recipe.setId(6L);
		when(recipeRepo.save(recipe)).thenReturn(recipe); 
				
		//act
		Recipe actual = controller.update(recipe, 7L); 
				
		//assert
		assertThat(recipe).isSameAs(actual); 
		verify(recipeRepo).save(recipe); 
	}
	
	@Test
	public void test_ingredient_is_created_for_recipe() {
		//arrange
		Recipe recipe = new Recipe(); 
		Ingredient ingredient = new Ingredient(); 
		when(recipeRepo.findOne(3L)).thenReturn(recipe); 
		when(ingredientRepo.findOne(5L)).thenReturn(ingredient); 
		
		//act
		Recipe actual = controller.createAnIngredient(3L, ingredient); 
		
		//assert
		assertThat(recipe).isSameAs(actual); 
//		verify(recipeRepo).save(recipe); 
	}
	
	@Test
	public void test_instruction_is_created_for_recipe() {
		//arrange
		Recipe recipe = new Recipe(); 
		Instruction instruction = new Instruction(); 
		when(recipeRepo.findOne(3L)).thenReturn(recipe); 
		when(instructionRepo.findOne(5L)).thenReturn(instruction); 
		
		//act
		Recipe actual = controller.createAnInstruction(3L, instruction); 
		
		//assert
		assertThat(recipe).isSameAs(actual); 
//		verify(recipeRepo).save(recipe); 
	}
	
	@Test
	public void test_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() {
		//arrange
		when(recipeRepo.findOne(1L)).thenThrow(new EmptyResultDataAccessException(0)); 
		
		//act
		Recipe actual = controller.delete(1L); 
		
		//assert
		assertThat(actual).isNull(); 
		verify(recipeRepo).findOne(1L); 
		
	}
	
	@Test
	public void test_delete_ingredient_returns_ingredient_deleted_when_found() {
		//arrange
		Recipe recipe = new Recipe(); 
		Ingredient ingredient = new Ingredient(); 
		when(recipeRepo.findOne(1L)).thenReturn(recipe);
		when(ingredientRepo.findOne(2L)).thenReturn(ingredient); 
		
		//act
		Recipe actual = controller.deleteIngredient(1L, 2L); 
		
		//assert
		assertThat(recipe).isSameAs(actual); 
		verify(recipeRepo).findOne(1L); 
		verify(ingredientRepo).delete(2L);
//		verify(ingredientRepo).findOne(2L);
		
	}
	
	@Test
	public void test_delete_instruction_returns_instruction_deleted_when_found() {
		//arrange
		Recipe recipe = new Recipe(); 
		Instruction instruction = new Instruction(); 
		when(recipeRepo.findOne(1L)).thenReturn(recipe);
		when(instructionRepo.findOne(2L)).thenReturn(instruction); 
		
		//act
		Recipe actual = controller.deleteInstruction(1L, 2L); 
		
		//assert
		assertThat(recipe).isSameAs(actual); 
		verify(recipeRepo).findOne(1L); 
		verify(instructionRepo).delete(2L);
//		verify(instructionRepo).findOne(2L);
		
	}
	
	@Test
	public void test_null_is_returned_when_delete_ingredient_throws_EmptyResultDataAccessException() {
		//arrange
		when(recipeRepo.findOne(1L)).thenThrow(new EmptyResultDataAccessException(0)); 
		when(ingredientRepo.findOne(2L)).thenThrow(new EmptyResultDataAccessException(0));
		//act
		Recipe actual = controller.deleteIngredient(1L, 2L); 
		
		//assert
		assertThat(actual).isNull(); 
		verify(recipeRepo).findOne(1L); 
//		verify(ingredientRepo).findOne(1L); 
		
	}
	
	@Test
	public void test_null_is_returned_when_delete_instruction_throws_EmptyResultDataAccessException() {
		//arrange
		when(recipeRepo.findOne(1L)).thenThrow(new EmptyResultDataAccessException(0)); 
		when(instructionRepo.findOne(2L)).thenThrow(new EmptyResultDataAccessException(0));
		//act
		Recipe actual = controller.deleteInstruction(1L, 2L); 
		
		//assert
		assertThat(actual).isNull(); 
		verify(recipeRepo).findOne(1L); 
//		verify(instructionRepo).findOne(1L); 
		
	}
	
}






















