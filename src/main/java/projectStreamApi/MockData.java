package projectStreamApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class MockData {
    public static List<Customer> generateCustomers() {

        Product productBookOne = new Product(321L, "JavaCore", "Books", new BigDecimal("150.00"));
        Product productBookTwo = new Product(543L, "Java Story", "Books", new BigDecimal("80.00"));
        Product productBookThree = new Product(53L, "Comedy", "Books", new BigDecimal("800.00"));
        Product productBookFour = new Product(43L, "Girls&Boys", "Books", new BigDecimal("105.00"));

        Product productChildrenOne = new Product(971L, "Baby-Diapers", "Children's products", new BigDecimal("300.00"));
        Product productChildrenTwo = new Product(698L, "Baby-Towel", "Children's products", new BigDecimal("230.99"));
        Product productChildrenThree = new Product(98L, "Baby-Bottle ", "Children's products", new BigDecimal("30.99"));

        Product productToysOne = new Product(12L, "Lego", "Toys", new BigDecimal("500.00"));
        Product productToysTwo = new Product(320L, "Helicopter", "Toys", new BigDecimal("1239.99"));
        Product productToysThree = new Product(2L, "Car", "Toys", new BigDecimal("487.80"));

        Product productFlowers = new Product(99L, "Rose", "Flowers", new BigDecimal("900.00"));
        Product productChocolate = new Product(43L, "Chocolate", "Nutella", new BigDecimal("658.99"));
        Product productVegetable = new Product(28L, "Tomatoes", "Vegetables", new BigDecimal("90.90"));

        Order orderOne = new Order(100L, LocalDate.now(),
                LocalDate.of(2026, 5, 29), "PROCESSING",
                Set.of(productBookOne, productBookTwo, productToysOne));
        Order orderTwo = new Order(101L, LocalDate.now(),
                LocalDate.of(2026, 5, 28), "DELIVERED",
                Set.of(productChildrenOne, productBookThree, productBookFour));
        Order orderThree = new Order(102L, LocalDate.now(),
                LocalDate.of(2026, 5, 27), "DELIVERED",
                Set.of(productBookThree, productChildrenTwo, productChildrenThree));
        Order orderFour = new Order(111L, LocalDate.now(),
                LocalDate.of(2026, 5, 26), "DELIVERED",
                Set.of(productToysOne, productChildrenOne, productToysTwo, productChildrenTwo, productChildrenThree,
                        productBookThree, productToysThree));
        Order orderFive = new Order(87, LocalDate.of(2021, 2, 22),
                LocalDate.of(2021, 3, 23), "DELIVERED",
                Set.of(productToysOne, productToysTwo, productToysThree));
        Order orderSix = new Order(66, LocalDate.of(2021, 3, 15),
                LocalDate.of(2021, 3, 16), "DELIVERED",
                Set.of(productFlowers, productChocolate, productVegetable, productBookThree));
        Order orderSeven = new Order(31, LocalDate.of(2021, 3, 14),
                LocalDate.of(2021, 3, 18), "DELIVERED",
                Set.of(productToysOne, productChocolate, productVegetable, productBookThree));
        Order orderEight = new Order(44, LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 6), "DELIVERED",
                Set.of(productToysOne, productChildrenOne, productToysTwo, productChildrenTwo, productChildrenThree,
                        productBookThree, productToysThree));


        Customer customerOne = new Customer(1L, "Ivan", 1L, Set.of(orderOne, orderTwo));
        Customer customerTwo = new Customer(2L, "Vito", 2L, Set.of(orderOne, orderTwo, orderThree));
        Customer customerThree = new Customer(3L, "Serge", 12L, Set.of(orderOne, orderTwo, orderThree, orderFour));
        Customer customerFour = new Customer(4L, "OLeg", 2L, Set.of(orderFive, orderSix));
        Customer customerFive = new Customer(5L, "Kim", 23L, Set.of(orderSix, orderFour));
        Customer customerSix = new Customer(6L, "Vlad", 4L, Set.of(orderSeven, orderFour));
        Customer customerSeven = new Customer(6L, "Vlad", 4L, Set.of(orderEight));

        return List.of(customerOne, customerTwo, customerThree, customerFour, customerFive, customerSix, customerSeven);
    }
}