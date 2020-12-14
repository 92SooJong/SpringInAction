package soojong.tacocloud.tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import soojong.tacocloud.tacos.Order;
import soojong.tacocloud.tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	//Order save(Order order);
	List<Order> findByUserOrderByPlacedAtDesc(User user,Pageable pageable);
}
