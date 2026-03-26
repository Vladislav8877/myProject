package projectException;

public class Validator {

    public static void checkText(String text,String fieldName) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: поле \""+fieldName+"\" не может быть пустым!");
        }
    }
    public static void checkYear(int year) {
        if (year < 1 || year > 2026) {
            throw new IllegalArgumentException("Ошибка: год должен быть в диапазоне от 1 до 2026!");
        }
    }
    public static void checkCopies(int copies) {
        if (copies < 0) {
            throw new IllegalArgumentException("Ошибка: копий должно быть больше 0!");
        }
    }
}

