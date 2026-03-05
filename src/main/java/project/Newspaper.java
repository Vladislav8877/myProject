package project;

import java.util.Objects;

public class Newspaper extends Publication implements Printable {

    private String publicationDay;

    Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        setPublicationDay(publicationDay);
    }

    public void setPublicationDay(String publicationDay) {
        if(publicationDay != null && !publicationDay.isEmpty()) {
            this.publicationDay = publicationDay;
        } else {
            throw new IllegalArgumentException("Поле \"День публикации\" не может быть пустым.");
        }
    }

    @Override
    public String toString() {
        return String.format("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nДень публикации: %s%n",
                getTitle(),getAuthor(),getYear(),publicationDay);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Newspaper newspaper = (Newspaper) obj;
        return getYear() == newspaper.getYear() &&(Objects.equals(publicationDay, newspaper.publicationDay));
    }
    @Override
    public int hashCode() {
        int result = publicationDay != null ? publicationDay.hashCode() : 0;
        result = 31 + result + getYear();
        return result;
    }

    @Override
    public void printDetails() {
        System.out.printf("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nДень публикации: %s%n",
                getTitle(),getAuthor(),getYear(),getPublicationDay());
    }

    public String getPublicationDay() {return publicationDay;}
    public String getType() {return "Newspaper";}
}
