package projectCollections;

public class Validator {

    public static void checkString(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: поле \""+fieldName+"\" не может быть пустым!");
        }
    }

    public static void checkNumber(String number, String fieldName) {
        checkString(number, fieldName);
        if (!number.matches("\\d+")) {
            throw new IllegalArgumentException("Ошибка: поле \""+fieldName+"\" должно содержать только цифры!");
        } else if (number.length() != 11) {
            throw new IllegalArgumentException("Ошибка: поле \""+fieldName+"\" должно содержать 11 символов!");
        }
    }

    public static void checkEmail(String email,String fieldName) {
        checkString(email, fieldName);
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Ошибка: поле \""+fieldName+"\" содержит неправильный формат записи!");
        }
    }
}
