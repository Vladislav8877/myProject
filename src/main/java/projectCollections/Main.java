package projectCollections;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static MySystem system = new MySystem();

    public static void menu() {
        System.out.println("||||ТЕЛЕФОННАЯ КНИГА||||");
        System.out.println(" МЕНЮ");
        System.out.println("(1) - Добавить контакт");
        System.out.println("(2) - Удалить Контакт");
        System.out.println("(3) - Список контактов");
        System.out.println("(4) - Найти контакт");
        System.out.println("(5) - Посмотреть контакты по группе");
        System.out.println("(0) - Выход");
    }

    public static void newContact() {
        String name, number, email, group;

        while (true) {
            try {
                System.out.print("Введите имя абонента: ");
                name = sc.nextLine().trim();
                Validator.checkString(name, "имя абонента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите номер абонента: ");
                number = sc.nextLine().trim();
                Validator.checkNumber(number, "номер абонента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите почту абонента: ");
                email = sc.nextLine().trim();
                Validator.checkEmail(email, "почта абонента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Группа абонента: ");
                group = sc.nextLine().trim();
                Validator.checkString(group,"группа абонента");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        system.addContact(name, number, email, group);
    }

    public static void delete() {
        while (true) {
            try {
                System.out.print("Введите имя абонента (или (0) для отмены): ");
                String name = sc.nextLine().trim();
                Validator.checkString(name,"имя абонента");
                if(name.equals("0")) {
                    break;
                }

                List<Contact> candidates = system.findContactByName(name);

                if(candidates.isEmpty()) {
                    System.out.println("Абонента: \""+name+"\" нет в телефонной книге");
                    return;
                }

                for (int i = 0; i < candidates.size(); i++) {
                    System.out.println((i + 1)+". "+candidates.get(i));
                }

                while (true) {
                    System.out.print("Выберите контакт для удаления (или (0) для отмены): ");
                    String input = sc.nextLine().trim();
                    if (input.equals("0")) {
                        System.out.println("Выход в главное меню...");
                        return;
                    }
                    try {
                        int choice = Integer.parseInt(input);
                        if (choice >= 1 && choice <= candidates.size()) {
                            Contact contactToDelete = candidates.get(choice - 1);
                            system.removeContact(contactToDelete);
                            System.out.println("Абонент " + contactToDelete.getName() + " полностью удален из телефонной книги.");
                            break;
                        } else {
                            System.out.println("Ошибка: неверный ввод...");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите цифру");
                    }
                }
            } catch (IllegalArgumentException | ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void allContacts() {
        Iterator<Contact> iterator = system.getAllContactsList().iterator();
        if (!iterator.hasNext()) {
            System.out.println("Телефонная книга пуста");
            return;
        }
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            System.out.println(contact);
        }
    }

    public static void searchContact() {
        while (true) {
            try {
            System.out.print("Введите имя абонента (или (0) для отмены): ");
            String name = sc.nextLine().trim();
            Validator.checkString(name,"имя абонента");
            if(name.equals("0")) {
                break;
            }

            system.searchContact(name);
            break;
        } catch (IllegalArgumentException | ItemNotFoundException e) {
            System.out.println(e.getMessage());}
        }
    }

    public static void searchByGroup() {
        while (true) {
            try {
                System.out.print("Введите название группы (или (0) для отмены): ");
                String groupName = sc.nextLine().trim();
                Validator.checkString(groupName, "название группы");
                if (groupName.equals("0")) {
                    break;
                }

                if (system.getGroups().isEmpty()) {
                    System.out.println("Телефонная книга пуста!");
                    return;
                }

                List<Contact> contacts = system.searchContactsByGroup(groupName);
                System.out.println("Группа: "+groupName);

                if(contacts.isEmpty()) {
                    System.out.println("Группа пуста");
                } else {
                    for (Contact contact : contacts) {
                        System.out.println(contact);
                    }
                }
                break;
            } catch (IllegalArgumentException | ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        do {
            menu();
            System.out.print("Сделай выбор: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1": newContact(); break;
                case "2": delete(); break;
                case "3": allContacts(); break;
                case "4": searchContact(); break;
                case "5": searchByGroup(); break;
                case "0": System.exit(0); break;
                default: System.out.println("Некорректный ввод!");
            }
        } while (true);
    }
}