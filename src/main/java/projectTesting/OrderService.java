package projectTesting;
import java.util.Optional;

public class OrderService {
    public static final String SUCCESS_MESSAGE = "Order processed successfully";
    public static final String FAILURE_MESSAGE = "Order processing failed";

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            int result = orderRepository.saveOrder(order);

            if (result >= 0) {
                return SUCCESS_MESSAGE;
            } else {
                return FAILURE_MESSAGE;
            }
        } catch (RuntimeException e) {
            return FAILURE_MESSAGE;
        }
    }

    public double calculateTotal(int id) {
        return orderRepository.getOrderById(id).map(Order::getTotalPrice).orElse(0.0);
    }
}