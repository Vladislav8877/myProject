package project;

import java.util.Objects;

abstract public class Publication {

    private String title;
    private String author;
    private int year;
    private static int publicationCount = 0;

    Publication(String title, String author, int year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public void setTitle(String title) {
        if(title!=null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Поле \"Название публикации\" - не может быть пустым.");
        }
    }

    public void setAuthor(String author) {
        if(author!=null && !author.isEmpty()) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Поле \"Автор публикации\" - не может быть пустым.");
        }
    }

    public void setYear(int year) {
        if(year > 0 && year <= 2026) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Поле \"Год выпуска\" - некорректно введен.");
        }
    }

    @Override
    public String toString() {
        return String.format("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%n",
                title,author,year);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Publication publication = (Publication)obj;
        return year == publication.year && (Objects.equals(author, publication.author))
                && (Objects.equals(title, publication.title));

    }
    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getYear() {return year;}
    public static void incrementCount(){
        publicationCount++;
    }
    public static int getPublicationCount() {return publicationCount;}
    abstract public String getType();

}
