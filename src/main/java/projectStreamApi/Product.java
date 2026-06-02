package projectStreamApi;
import java.math.BigDecimal;

public class Product {

    private final long id;
    private final String name;
    private final String category;
    private final BigDecimal price;

    public Product(long id, String name, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public  String getCategory() {return category;}
    public BigDecimal getPrice() {return price;}

    @Override
    public String toString() {
        return String.format("ID: %d || Name: %s || Category: %s || Price: %s", id, name, category, price);
    }
}