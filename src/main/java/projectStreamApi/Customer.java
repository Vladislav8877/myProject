package projectStreamApi;
import java.util.Set;

public class Customer {

    private final long id;
    private final String name;
    private final long level;
    private final Set<Order> orders;

    public Customer(long id, String name, long level, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.orders = orders;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public long getLevel() {return level;}
    public Set<Order> getOrders() {return orders;}

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Level: %d", id, name, level);
    }
}