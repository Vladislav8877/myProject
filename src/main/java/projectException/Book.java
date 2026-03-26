package projectException;
import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private int year;
    private int availableCopies = 0;

    Book(String title, String author, int year, int availableCopies) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setCopies(availableCopies);
    }

    public void setTitle(String title) {
        Validator.checkText(title,"название книги");
        this.title = title;
    }

    public void setAuthor(String author) {
        Validator.checkText(author,"имя автора");
        this.author = author;
    }

    public void setYear(int year) {
        Validator.checkYear(year);
        this.year = year;
    }

    public void setCopies(int copies) {
        Validator.checkCopies(copies);
        this.availableCopies = copies;
    }

    @Override
    public String toString() {
        return String.format("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nКоличество копий: %d%n",
                title, author, year, availableCopies);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return year == book.year && (Objects.equals(author,book.author)) && (Objects.equals(title,book.title));
    }
    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getYear() {return year;}
    public int getCopies() {return availableCopies;}
}
