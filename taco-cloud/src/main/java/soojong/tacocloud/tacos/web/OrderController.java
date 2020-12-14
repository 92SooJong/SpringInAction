package soojong.tacocloud.tacos.web;

import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import soojong.tacocloud.tacos.Order;
import soojong.tacocloud.tacos.User;
import soojong.tacocloud.tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	private OrderProps props;
	
	
	
	
	public OrderController(OrderRepository orderRepo,OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props; // 주입되면 props가 자동으로 적용됨.
	}
	
	
	@GetMapping
	public String orderForUser(@AuthenticationPrincipal User user, Model model) {
		
		Pageable pageable = PageRequest.of(0, 20);
		model.addAttribute("orders",orderRepo.findByUserOrderByPlacedAtDesc(user,pageable));
		return "orderList";
	}
	
	
	
	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user , @ModelAttribute Order order ) {
		
		if (order.getDeliveryName() == null) {
			  order.setDeliveryName(user.getFullname());
		}
		if (order.getDeliveryStreet() == null) {
			  order.setDeliveryStreet(user.getStreet());
		}
		if (order.getDeliveryCity() == null) {
			  order.setDeliveryCity(user.getCity());
		}
		if (order.getDeliveryState() == null) {
			  order.setDeliveryState(user.getState());
		}
		if (order.getDeliveryZip() == null) {
			  order.setDeliveryZip(user.getZip());
		}
		
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order,Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setUser(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		//log.info("Order submitted: " + order);
		return"redirect:/";
	}
}
