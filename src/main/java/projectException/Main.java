package projectException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static Library library = new Library();

    public static void newObject() {

         String title;
         String author;
         int year;
         int copies;

        while (true) {
            try {
                System.out.print("Введите название книги: ");
                title = sc.nextLine().trim();
                Validator.checkText(title, "название книги");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите имя автора: ");
                author = sc.nextLine().trim();
                Validator.checkText(author, "имя автора");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите год выпуска: ");
                year = Integer.parseInt(sc.nextLine().trim());
                Validator.checkYear(year);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: поле должно содержать только цифры!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите количество копий: ");
                copies = Integer.parseInt(sc.nextLine().trim());
                Validator.checkCopies(copies);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: поле должно содержать только цифры!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        library.addBook(title, author, year, copies);
        System.out.println("Вы добавили новую публикацию в каталог");
    }


    public static void menu() {
        System.out.println("||||БИБЛИОТЕКА||||");
        System.out.println(" МЕНЮ");
        System.out.println("(1) - Вывести каталог");
        System.out.println("(2) - Добавить объект");
        System.out.println("(3) - Выдать объект");
        System.out.println("(4) - Вернуть объект");
        System.out.println("(5) - Выйти из приложения");
    }

    public static void main(String[] args) {


        do {
            menu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    for (Book allBooks : library.getAllBooks()) {
                        System.out.println(allBooks);
                    } break;
                case "2":
                    newObject(); break;
                case "3":
                    while (true) {
                        try {
                            System.out.print("Введите название книги (или введите '0' для отмены): ");
                            String bookName = sc.nextLine().trim();
                            if(bookName.equals("0")) {
                                break;
                            }
                            Validator.checkText(bookName, "название книги");
                            library.takeBook(bookName);
                            break;
                        } catch (IllegalArgumentException | ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (NoAvailableCopiesException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } break;
                case "4":
                    while (true) {
                        try {
                            System.out.print("Введите название книги (или '0' для отмены): ");
                            String bookName = sc.nextLine().trim();
                            if(bookName.equals("0")) {
                                break;
                            }
                            Validator.checkText(bookName,"название книги");
                            library.returnBook(bookName);
                            break;
                        } catch (IllegalArgumentException | ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } break;
                case "5": System.exit(0); break;
                default: System.out.println("Некорректный ввод!");
            }
        }while(true);
    }
}

