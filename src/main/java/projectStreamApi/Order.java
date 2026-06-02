package projectStreamApi;
import java.time.LocalDate;
import java.util.Set;

public class Order {

    private final long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final String status;
    private final Set<Product> products;

    public Order(long id, LocalDate orderDate, LocalDate deliveryDate, String status, Set<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.products = products;
    }

    public long getId() {return id;}
    public LocalDate getOrderDate() {return orderDate;}
    public LocalDate getDeliveryDate() {return deliveryDate;}
    public String getStatus() {return status;}
    public Set<Product> getProducts() {return products;}

    @Override
    public String toString() {
        return String.format("ID: %d | OrderDate: %s |  DeliveryDate: %s | Status: %s ",
                id, orderDate, deliveryDate, status);
    }
}