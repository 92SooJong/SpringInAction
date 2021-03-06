package soojong.tacocloud.tacos.web;

import java.security.Principal;
import java.util.ArrayList;
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
import soojong.tacocloud.tacos.User;
import soojong.tacocloud.tacos.UserRepository;
import soojong.tacocloud.tacos.data.IngredientRepository;
import soojong.tacocloud.tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	private UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo , TacoRepository tacoRepo, UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.userRepo = userRepo;
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
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}
	
	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
		
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		// Ingredient내 Type배열을 반환
		Type[] types = Ingredient.Type.values();
		// types를 순회함
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
		}
		
		//model.addAttribute("taco",new Taco());
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user",user);
		
		
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
