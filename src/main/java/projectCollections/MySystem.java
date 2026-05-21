package projectCollections;
import java.util.*;
public class MySystem {

    List<Contact> listPhoneBook = new ArrayList<>();
    Set<Contact> setPhoneBook = new HashSet<>();
    Map<String, List<Contact>> groups = new HashMap<>(); // будет создан в Node(value) ссылка на ArrayList

    public void addContact(String name, String number, String email, String group) {
        Contact contact = new Contact(name, number, email, group);

        if (setPhoneBook.contains(contact)) {
            System.out.println("Ошибка: контакт с таким номер уже существует в телефонной книге");
        } else {
            setPhoneBook.add(contact);
            listPhoneBook.add(contact);
            groups.computeIfAbsent(group, k -> new ArrayList<>()).add(contact);
            System.out.println("Контакт добавлен");
            // Найди "Группу" || Если её нет -> создай новый ArrayList
            }
        }

    public List<Contact> findContactByName(String name) {
        List<Contact> sameContacts = new ArrayList<>();
        for (Contact contacts : listPhoneBook) {
            if (contacts.getName().equalsIgnoreCase(name)) {
                sameContacts.add(contacts);
            }
        }
        return sameContacts;
    }

    public void removeContact(Contact contactToDelete) {
        listPhoneBook.remove(contactToDelete);
        setPhoneBook.remove(contactToDelete);
        for(List<Contact> group : groups.values()) { // проходим по всем коробкам и удаляем отовсюду
            group.remove(contactToDelete);
        }
    }

    public void searchContact(String name) {
        Set<Contact> contacts = new HashSet<>();
        for (Contact contact : setPhoneBook) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.add(contact);
            }
        }
        if (contacts.isEmpty()) {
            System.out.println("Абонента: \""+name+"\" нет в телефонной книге ");
            return;
        }

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public List<Contact> searchContactsByGroup(String groupName) {
        for (String key : groups.keySet()) {
            if (key.equalsIgnoreCase(groupName)) {
                return groups.get(key);
            }
        }
        throw new ItemNotFoundException("Группы \"" + groupName + "\" не существует в телефонной книге");
    }

    public List<Contact> getAllContactsList() {return listPhoneBook;}
    public Set<Contact> getAllContactsSet() {return setPhoneBook;}
    public Map<String, List<Contact>> getGroups() {return groups;}
}