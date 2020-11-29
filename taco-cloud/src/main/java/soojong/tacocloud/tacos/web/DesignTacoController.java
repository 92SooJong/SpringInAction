package soojong.tacocloud.tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import soojong.tacocloud.tacos.Ingredient;
import soojong.tacocloud.tacos.Ingredient.Type;
import soojong.tacocloud.tacos.Order;
import soojong.tacocloud.tacos.Taco;
import soojong.tacocloud.tacos.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@ModelAttribute(name ="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="taco")
	public Taco taco() {
		return new Taco();
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design,Errors errors,@ModelAttribute Order order) {
		
		if(errors.hasErrors()) {
			return "design";
		}
		// 여기작성필요.
		Taco saved = tacoRepo.save(design);
		
		
		return "redirect:/orders/current";
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
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
