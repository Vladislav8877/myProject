package projectCollections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    @Test
    @DisplayName("Проверка: пустая строка вызывает ошибку")
    void shouldThrowExceptionOnEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> Validator.checkString("","имя абонента"));
    }

    @Test
    @DisplayName("Проверка: строка из пробелов вызывает ошибку")
    void shouldThrowExceptionOnBlankString() {
        assertThrows(IllegalArgumentException.class, () -> Validator.checkString("  ","имя абонента"));
    }

    @Test
    @DisplayName("Проверка: правильная строка не вызывает ошибку")
    void shouldNotThrowException() {
        assertDoesNotThrow(() -> Validator.checkString("Vladislav","имя абонента"));
    }

    // ---

    @Test
    @DisplayName("Проверка: email без @ вызывает ошибку")
    void shouldThrowExceptionOnEmail() {
        assertThrows(IllegalArgumentException.class, () -> Validator.checkEmail("vlad.gmail.com","почта абонента"));
    }

    @Test
    @DisplayName("Проверка: правильный email не вызывает ошибку")
    void shouldNotThrowExceptionOnEmail() {
        assertDoesNotThrow(() -> Validator.checkEmail("vlad@gmail.com","почта абонента"));
    }

    // ---

    @Test
    @DisplayName("Проверка: содержание букв в номере абонента вызывает ошибку")
    void shouldThrowExceptionWhenNumberWithString() {
        assertThrows(IllegalArgumentException.class, () -> Validator.checkNumber("8989709358k","номер абонента"));
    }

    @Test
    @DisplayName("Проверка: длина номера меньше '11' символов вызывает ошибку")
    void shouldThrowExceptionWhenNumberSmallSize() {
        assertThrows(IllegalArgumentException.class, () -> Validator.checkNumber("8989709358","номер абонента"));
    }

    @Test
    @DisplayName("Проверка: правильный номер не вызывает ошибку")
    void shouldNotThrowExceptionOnNumber() {
        assertDoesNotThrow(() -> Validator.checkNumber("89897093585","номер абонента"));
    }
}

