package project;
import java.util.Scanner;

import static project.Publication.getPublicationCount;


public class Main {

    static Scanner sc = new Scanner(System.in);
    static Library library = new Library();

    private static int typeInteger(String message) {
        String input;
        while(true) {
            System.out.println(message);
            input = sc.nextLine().trim();

            if(input.isEmpty()) {
                System.out.println("Поле не может быть пустым. Попробуйте ещё...");
                continue;
            }
            if(input.matches("[1-3]")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Поле должно содержать цифры от 1 до 3");
            }
        }
    }

    private static String notEmptyString(String message) {
        String input;
        while(true) {
            System.out.print(message);
            input = sc.nextLine().trim();

            if(!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не может быть \"пустым\". Попробуйте еще раз...");
        }
    }

    private static int notNegativeInteger(String message) {
        String input;
        while(true) {
            System.out.print(message);
            input = sc.nextLine().trim();

            if(input.isEmpty()){
                System.out.println("Поле не может быть пустым. Попробуйте ещё...");
                continue;
            }
            if(input.matches("\\d+")){
                int value = Integer.parseInt(input);

                if(value > 0 && value <= 2026){
                    return value;
                } else {
                    System.out.println("Число в поле должно быть от 1 до 2026.");
                }
            } else {
                System.out.println("Поле должно содержать только цифры!");
            }
        }
    }

    private static int issueNumber(String message) {
        String input;
        while(true) {
            System.out.print(message);
            input = sc.nextLine().trim();

            if(input.isEmpty()) {
                System.out.println("Поле не может быть пустым. Попробуйте ещё...");
                continue;
            }
            if(input.matches("\\d+")){
                return Integer.parseInt(input);
            } else {
                System.out.println("Поле должно содержать только цифры!");
            }
        }
    }

    private static void newPublication(){
        System.out.println("|||Добавление Новой Публикации|||");
        int type = typeInteger("Выберите тип: (1) - Книга || (2) - Журнал || (3) - Газета");

        String title = notEmptyString("Введите название: ");
        String author = notEmptyString("Введите автора: ");
        int year = notNegativeInteger("Введите год: ");

        switch(type){

            case 1:
                String ISBN = notEmptyString("Введите ISBN: ");
                library.addPublication(new Book(title,author,year,ISBN));
                break;

            case 2:
                int issueNumber = issueNumber("Номер выпуска: ");
                library.addPublication(new Magazine(title,author,year,issueNumber));
                break;

            case 3:
                String publicationDay = notEmptyString("Введите день выпуска: ");
                library.addPublication(new Newspaper(title,author,year,publicationDay));
                break;
        }
        System.out.println("Публикация Успешно Добавлена!");
    }

    private static void search(){
        System.out.println("|||Поиск Автора|||");
        String searchName = notEmptyString("Введите Имя: ");
        library.searchByAuthor(searchName);
    }

    private static void showMenu(){
        System.out.println("||||БИБЛИОТЕКА||||");
        System.out.println(" МЕНЮ");
        System.out.println("(1) - Добавить новую публикацию");
        System.out.println("(2) - Вывести список всех публикаций");
        System.out.println("(3) - Поиск публикации по автору");
        System.out.println("(4) - Вывести общий список публикации");
        System.out.println("(0) - Выйти");
    }

    public static void main(String[] args) {

        do{
            showMenu();
            String choice = sc.nextLine().trim();
            switch(choice) {
                case "1": newPublication(); break;
                case "2": library.listPublications(); break;
                case "3": search(); break;
                case "4":
                    System.out.println("Общее количество публикации: "+getPublicationCount()); break;
                case "0": System.exit(0); break;
                default : System.out.println("Некорректный ввод!");
            }
        }while(true);
    }
}


