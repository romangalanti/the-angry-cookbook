package com.libertymutual.goforcode.theangrycookbook.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.theangrycookbook.models.Recipe;
import com.libertymutual.goforcode.theangrycookbook.repositories.IngredientRepository;
import com.libertymutual.goforcode.theangrycookbook.repositories.InstructionRepository;
import com.libertymutual.goforcode.theangrycookbook.repositories.RecipeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recipe")
@Api(description="Use this to get and create recipes, and add ingredients and instructions to recipes.")
public class RecipeApiController {
	
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	private RecipeRepository recipeRepo;
	
	public RecipeApiController(IngredientRepository ingredientRepo, InstructionRepository instructionRepo, RecipeRepository recipeRepo) {
		this.ingredientRepo = ingredientRepo;
		this.instructionRepo = instructionRepo;
		this.recipeRepo = recipeRepo;
	}
	
	@ApiOperation(value = "Get a list of all of the recipes.")
	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();
	}
	
	@ApiOperation(value = "Get the details of a single recipe.")
	@GetMapping("{id}")
	public Recipe getOne(@PathVariable long id) throws StuffNotFoundException {
		Recipe recipe = recipeRepo.findOne(id);
		if (recipe == null) {
			throw new StuffNotFoundException();
		}
		return recipe;
	}
	
	@ApiOperation(value = "Create a new recipe.")
	@PostMapping("")
	public Recipe create(@RequestBody Recipe recipe) {
		return recipeRepo.save(recipe);
	}
	
	@ApiOperation(value = "Delete the recipe with the given id.")
	@DeleteMapping("{id}")
	public Recipe delete(@PathVariable long id) {
		try {
			Recipe recipe = recipeRepo.findOne(id);
			recipeRepo.delete(id);
			return recipe;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@ApiOperation(value = "Update the title, description, and # of minutes for a recipe.")
	@PutMapping("{id}")
	public Recipe update(@RequestBody Recipe recipe, @PathVariable long id) {
		recipe.setId(id);
		return recipeRepo.save(recipe);
	}

}
