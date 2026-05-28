package projectStreamApi;
import org.w3c.dom.ls.LSOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Customer> customers = MockData.generateCustomers();

        //Задание 1.
        List<Product> expensiveBooks = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .filter(n -> n.getCategory().equals("Book"))
                .filter(n -> n.getPrice().compareTo(new BigDecimal("100")) > 0)
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .toList();
        System.out.println("Задание 1: "+expensiveBooks);
        System.out.println("-----------------");


        //---

        //Задание 2.
        List<Product> onlyChildrenProduct = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .filter(n -> n.getCategory().equals("Children's category"))
                .distinct()
                .sorted(Comparator.comparing(Product::getId))
                .toList();
        System.out.println("Задание 2: "+onlyChildrenProduct);
        System.out.println("-----------------");

        //---

        //Задание 3.
        BigDecimal salesForToysCategory = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .filter(n -> n.getCategory().equals("Toys"))
                .map(n -> n.getPrice().multiply(new BigDecimal("0.90")))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("Задание 3: "+salesForToysCategory);
        System.out.println("-----------------");
        //---

        //Задание 4.
        List<Product> fromFebruaryToApril = customers.stream()
                //.peek(n -> System.out.println("Покупатели: "+n))
                .filter(n -> n.getLevel() >= 2)
                .flatMap(n -> n.getOrders().stream())
                //.peek(n -> System.out.println("Заказы покупателей 2 и > LVL: "+n))
                .filter(n -> !n.getOrderDate().isBefore(LocalDate.of(2021,2,1))
                        && !n.getDeliveryDate().isAfter(LocalDate.of(2021,4,1)))
                .flatMap(n -> n.getProducts().stream())
                .toList();
        System.out.println("Задание 4: "+fromFebruaryToApril);
        System.out.println("-----------------");

        //---

        //Задание 5.
        List<Product> cheaperBooks = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .filter(n -> n.getCategory().equals("Book"))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();
        System.out.println("Задание 5: "+cheaperBooks);
        System.out.println("-----------------");

        //---

        //Задание 6.
        List<Order> threeLastOrders = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .filter(n -> n.getStatus().equals("DELIVERED"))
                .sorted(Comparator.comparing(Order::getDeliveryDate).reversed())
                .distinct()
                .limit(3)
                //.peek(n -> System.out.println("Сортированные элементы: "+n))
                .toList();
        System.out.println("Задание 6: "+threeLastOrders);
        System.out.println("-----------------");

        //---

        //Задание 7.
        List<Product> fifteenthOfMarch = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .filter(n -> n.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .distinct()
                .peek(n -> System.out.println("Найден заказ от 15.03.2021, ID: " + n.getId()))
                .flatMap(n -> n.getProducts().stream())
                .distinct()
                .sorted(Comparator.comparing(Product::getId))
                .toList();
        System.out.println("Задание 7: " + fifteenthOfMarch);
        System.out.println("-----------------");

        //---

        //Задание 8.
        BigDecimal mayPrice = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .filter(n -> n.getOrderDate().getMonth() == Month.MAY)
                .flatMap(n -> n.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("Задание 8: "+mayPrice);
        System.out.println("-----------------");

        //---

        //Задание 9.
        List<BigDecimal> midlPriceForMarch = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .filter(n -> n.getOrderDate().equals(LocalDate.of(2021,3,15)))
                .distinct()
                .flatMap(n -> n.getProducts().stream())
                .map(Product::getPrice)
                .toList();
        System.out.println(midlPriceForMarch);

        int size = midlPriceForMarch.size();

        BigDecimal totalSum = midlPriceForMarch.stream()
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal result = totalSum.divide(BigDecimal.valueOf(size),2,RoundingMode.HALF_UP); // округляем по правилам математики
        System.out.println("Задание 9: "+result);
        System.out.println("-----------------");

        //---

        //Задание 10.

        DoubleSummaryStatistics refreshBook = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .filter(n -> n.getCategory().equals("Book"))
                .mapToDouble(n -> n.getPrice().doubleValue()) // перевожу BigDecimal в double
                .summaryStatistics();

        System.out.println("Задание 10: (Статистика по книгам)");
        System.out.println("Кол-во проданных книг: "+refreshBook.getCount());
        System.out.println("Общая сумма выручки: "+refreshBook.getSum());
        System.out.println("Средний чек за книгу: "+refreshBook.getAverage());
        System.out.println("Самая дорогая книга: "+refreshBook.getMax());
        System.out.println("Самая дешевая книга: "+refreshBook.getMin());
        System.out.println("-----------------");

        //---

        //Задание 11.

        Map<Long, Integer> orderAndProductCount = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(Order::getId,n -> n.getProducts().size()));
        System.out.println("Задание 11: "+orderAndProductCount);
        System.out.println("-----------------");

        //---

        //Задание 12.

        Map<Customer, Set<Order>> customerAndOrderCount = customers.stream()
                        .collect(Collectors.toMap(Function.identity(), Customer::getOrders));
                        //.collect(Collectors.toMap(n -> n, n -> n.getOrders()));
        System.out.println("Задание 12: "+customerAndOrderCount);

        //---

        //Задание 13.

        Map<Order,Double> ordersAndSums = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(order -> order, order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO,BigDecimal::add).doubleValue()));
        System.out.println("Задание 13: "+ordersAndSums);

        //---

        //Задание 14.

        Map<String, List<String>> categoryAndNamesOfProducts = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));
        System.out.println("Задание 14: "+categoryAndNamesOfProducts);

        //---

        //Задание 15.

        Map<String,Product> mostExpensiveProducts = customers.stream()
                .flatMap(n -> n.getOrders().stream())
                .flatMap(n -> n.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Product::getPrice)),Optional::get)));
        System.out.println("Задание 15: "+mostExpensiveProducts);
    }
}
