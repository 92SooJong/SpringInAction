package soojong.tacocloud.tacos.data;

import soojong.tacocloud.tacos.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	Ingredient findById(String id);
	Ingredient save(Ingredient ingredient);
}
