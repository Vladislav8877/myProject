package project;

import java.util.Objects;

public class Book extends Publication implements Printable{

    private String ISBN;

    Book(String title, String author, int year, String ISBN) {
        super(title,author,year);
        setISBN(ISBN);
    }

    public void setISBN(String ISBN) {
        if (ISBN != null && !ISBN.isEmpty()) {
            this.ISBN = ISBN;
        } else {
            throw new IllegalArgumentException("Поле \"ISBN\" - не может быть пустым.");
        }
    }

    @Override
    public String toString() {
        return String.format("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nISBN: %s%n",
                getTitle(),getAuthor(),getYear(),ISBN);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return getYear() == book.getYear() && (Objects.equals(ISBN, book.ISBN));
    }
    @Override
    public int hashCode() {
        int result = ISBN != null ? ISBN.hashCode() : 0;
        result = 31 * result + getYear();
        return result;
    }

    @Override
    public void printDetails() {
        System.out.printf("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nISBN: %s%n",
                getTitle(),getAuthor(),getYear(),getISBN());
    }

    public String getISBN() {return ISBN;}
    public String getType() {return "Book";}
}
