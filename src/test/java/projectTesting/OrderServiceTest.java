package projectTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class OrderServiceTest {
    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    private Order createOrder(int id, String productName, int quantity, double unitPrice) {
        return new Order(id,productName,quantity,unitPrice);
    }

    @Test
    @DisplayName("Тест по заданию №1: Успешная обработка заказа")
    void shouldReturnSuccessMessageWhenOrderIsSaved() {
        Order correctOrder = createOrder(0,"Iphone",10,83_000.0);
        when(repository.saveOrder(correctOrder)).thenReturn(11);

        String result = service.processOrder(correctOrder);

        assertEquals("Order processed successfully",result,"Ошибка: результат сохранения должен вывести \"Order processed successfully\"");
        verify(repository,times(1)).saveOrder(correctOrder);
    }

    @Test
    @DisplayName("Тест по заданию №1: Неудачная обработка заказа")
    void shouldReturnErrorMessageWhenRepositoryThrowsException() {
        Order wrongOrder = createOrder(98,"Iphone",10,83_000.0);
        when(repository.saveOrder(wrongOrder)).thenThrow(new RuntimeException("DB is Down!"));

        String result = service.processOrder(wrongOrder);

        assertEquals("Order processing failed",result,"когда БД падает - сервис должен вернуть аккуратное сообщение");
        verify(repository,times(1)).saveOrder(wrongOrder);
    }

    //---

    @Test
    @DisplayName("Тест по заданию №2: Успешное вычисление стоимости")
    void shouldReturnCorrectTotalWhenOrderExists() {
        int orderId = 32;
        Order correctOrder = createOrder(orderId,"pen",3,100.0);
        when(repository.getOrderById(orderId)).thenReturn(Optional.of(correctOrder));

        double result = service.calculateTotal(orderId);

        assertEquals(300.0,result,"Ошибка: результат вычисление должен быть равен - 300.0");
        verify(repository,times(1)).getOrderById(orderId);
    }

    @Test
    @DisplayName("Тест по заданию №2: Заказ не найден")
    void shouldReturnZeroWhenOrderIsNull () {
        int orderId = 0;
        when(repository.getOrderById(orderId)).thenReturn(Optional.empty());

        double result = service.calculateTotal(orderId);

        assertEquals(0.0,result,"Ошибка: пустой Optional должен вернуть 0.0 по реализации");
        verify(repository,times(1)).getOrderById(orderId);
    }

    @Test
    @DisplayName("Тест по заданию №2: Корректное вычисление с нулевым количеством или ценой")
    void shouldReturnZeroWhenQuantityAndPriceAreZero() {
        int orderId = 43;
        Order normalOrder = createOrder(orderId,"gloves",0,0.0);
        when(repository.getOrderById(orderId)).thenReturn(Optional.of(normalOrder));

        double result = service.calculateTotal(orderId);

        assertEquals(0.0,result,"Ошибка: расчет итоговой стоимости для граничных значений (0) выполнен неверно");
        verify(repository,times(1)).getOrderById(orderId);
        //можно изменить orElse в методе calculateTotal на 99.0,да бы убедиться в том, что это приходит результат вычисления
    }
}