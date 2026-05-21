package projectCollections;

import java.util.Objects;

public class Contact {

    private String name;
    private String number;
    private String email;
    private String group;

    Contact(String name, String number, String email, String group) {
        setName(name);
        setNumber(number);
        setEmail(email);
        setGroup(group);
    }

    public void setName(String name) {
        Validator.checkString(name,"имя абонента");
        this.name = name;
    }

    public void setNumber(String number) {
        Validator.checkNumber(number,"номер абонента");
        this.number = number;
    }

    public void setEmail(String email) {
        Validator.checkEmail(email,"почта абонента");
        this.email = email;
    }

    public void setGroup(String group) {
        Validator.checkString(group,"группа");
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s || Номер: %s || Почта: %s || Группа: %s", name, number, email, group);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return  Objects.equals(number, contact.number) && Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    public String getName() {return name;}
    public String getNumber() {return number;}
    public String getEmail() {return email;}
    public String getGroup() {return group;}
}
