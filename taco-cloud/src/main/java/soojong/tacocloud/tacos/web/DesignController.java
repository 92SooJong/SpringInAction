package soojong.tacocloud.tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import soojong.tacocloud.tacos.Ingredient;
import soojong.tacocloud.tacos.Ingredient.Type;
import soojong.tacocloud.tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		// 재료들을 담은 ingredients 배열
		List<Ingredient> ingredients = Arrays.asList(
			      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			      new Ingredient("CHED", "Cheddar", Type.CHEESE),
			      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			      new Ingredient("SLSA", "Salsa", Type.SAUCE),
			      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
			    );
		
		// Ingredient내 Type배열을 반환
		Type[] types = Ingredient.Type.values();
		// types를 순회함
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
		}
		
		model.addAttribute("taco",new Taco());
		
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		// 해석필요....
		return ingredients
				.stream()
				.filter( x-> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
}
