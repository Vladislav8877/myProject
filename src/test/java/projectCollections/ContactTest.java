package projectCollections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {
        private Contact contact;

        @BeforeEach
        void setUp() {
            contact = new Contact("Vlad","89897093585","vladazarov@gmail.com","work");
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Проверка: Конструктор правильно сохраняет данные")
        void shouldCreateContactWithCorrectFields() {
            assertEquals("Vlad",contact.getName(),"Имя из конструктора сохранилось неверно");
            assertEquals("89897093585",contact.getNumber(),"Номер из конструктора сохранился неверно");
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Проверка: Сеттеры корректно обновляют данные")
        void shouldUpdateFieldsWithSetters() {
            contact.setName("Ivan");
            contact.setNumber("89896301406");

            assertEquals("Ivan",contact.getName(),"Сеттеры имени некорректно отработали");
            assertEquals("89896301406",contact.getNumber(),"Сеттеры номера некорректно отработали");
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Проверка: Сеттер имени выбрасывает ошибку при пустой строке")
        void shouldThrowExceptionWhenNameIsEmpty() {
            assertThrows(IllegalArgumentException.class, () -> contact.setName(""));
        }

        @Test
        @DisplayName("Проверка: Сеттер номера выбрасывает ошибку при пустой строке")
        void shouldThrowExceptionWhenNumberIsEmpty() {
            assertThrows(IllegalArgumentException.class, () -> contact.setNumber(""));
        }
}