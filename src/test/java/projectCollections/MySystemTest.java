package projectCollections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySystemTest {
    private MySystem system;

    @BeforeEach
    void setUp() {
        system = new MySystem();
    }

    @Test
    @DisplayName("Проверка: Добавление уникального контакта увеличивает размер списка")
    void shouldAddUniqueContact() {
        system.addContact("Dima","89896709070","dimon@yandex.ru","classMate");
        assertEquals(1,system.getAllContactsList().size(),"Контакт не добавлен в коллекцию");
    }

    @Test
    @DisplayName("Проверка: Удаление уникального контакта уменьшает размер списка")
    void shouldRemoveUniqueContact() {
        system.addContact("Dima","89896709070","dimon@yandex.ru","classMate");
        Contact contactToDelete = system.findContactByName("Dima").getFirst();
        system.removeContact(contactToDelete);
        assertEquals(0,system.getAllContactsList().size(),"Контакт не был удален из коллекции");
    }

    @Test
    @DisplayName("Проверка: Абонент должен быть найден в коллекции по его имени")
    void shouldFindContactByName() {
        system.addContact("Dima","89896709070","dimon@yandex.ru","classMate");
        system.addContact("dima","89896707090","dimas@yandex.ru","work");

        Contact searchContact = system.findContactByName("Dima").getFirst();
        assertEquals("Dima",searchContact.getName(),"Контакт не был найден в коллекции");

    }

    @Test
    @DisplayName("Проверка: Абоненты должны быть найдены в коллекции по их группе")
    void shouldFindContactsByGroup() {
        system.addContact("Father","89187876776","father@yandex.ru","family");
        system.addContact("Mather","89187099009","mother@yandex.ru","family");
        system.addContact("Roman","89187044889","roma@yandex.ru","work");

        List<Contact> familyContact = system.searchContactsByGroup("family");
        assertEquals(2,familyContact.size(),"В группе должно быть 2 контакта");

        assertEquals("Father",familyContact.getFirst().getName(),"Первый контакт в группе не совпадает");
        assertEquals("Mather",familyContact.get(1).getName(),"Второй контакт в группе не совпадает");
    }
}
