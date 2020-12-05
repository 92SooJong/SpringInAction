package soojong.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;

import soojong.tacocloud.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String>{
	
	/*
	 * Iterable<Ingredient> findAll(); Ingredient findById(String id); Ingredient
	 * save(Ingredient ingredient);
	 */
}
