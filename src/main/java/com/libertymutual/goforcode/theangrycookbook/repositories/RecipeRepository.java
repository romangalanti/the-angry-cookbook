package com.libertymutual.goforcode.theangrycookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.theangrycookbook.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
