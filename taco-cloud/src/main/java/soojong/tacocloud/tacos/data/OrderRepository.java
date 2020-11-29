package soojong.tacocloud.tacos.data;

import soojong.tacocloud.tacos.Order;

public interface OrderRepository {
	Order save(Order order);
}
