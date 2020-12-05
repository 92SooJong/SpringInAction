package soojong.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;

import soojong.tacocloud.tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	//Order save(Order order);
}
