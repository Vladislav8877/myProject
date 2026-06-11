package projectTesting;

import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            int result = orderRepository.saveOrder(order);

            if (result >= 0) {
                return "Order processed successfully";
            } else {
                return "Order processing failed";
            }
        } catch (RuntimeException e) {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Optional<Order> order = orderRepository.getOrderById(id);
        return order.map(Order::getTotalPrice).orElse(0.0);
    }
}