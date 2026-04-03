package projectException;
import java.util.ArrayList;
import java.util.List;

public class Library {

    public List<Book> catalogs;
    Library() {
        catalogs = new ArrayList<>();
    }

    public void addBook(String title, String author, int year, int availableCopies) {
        catalogs.add(new Book(title, author, year, availableCopies));

    }

    public void takeBook(String title) throws ItemNotFoundException, NoAvailableCopiesException {
        Book foundBook = null;
        for (Book book : catalogs) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBook = book;
                break;
            }
        }

            if (foundBook == null) {
                throw new ItemNotFoundException("Название книги: \"" + title + "\" не существует в каталоге.");
            } else if (foundBook.getCopies() < 1) {
                throw new NoAvailableCopiesException("Книги: \"" + title + "\" 0 шт. в каталоге");
            } else {
                foundBook.setCopies(foundBook.getCopies() - 1);
                System.out.println("Книга: \"" + title + "\" выдана!\nВ каталоге осталось: " + foundBook.getCopies()+" шт.");
            }
        }

    public void returnBook(String title) throws ItemNotFoundException {
        Book returnBook = null;
        for (Book book : catalogs) {
            if(book.getTitle().equalsIgnoreCase(title)) {
                returnBook = book;
                break;
            }
        }

        if(returnBook == null) {
            throw new ItemNotFoundException("Название книги: \"" + title + "\" не существует в каталоге.");
        } else {
            returnBook.setCopies(returnBook.getCopies() + 1);
            System.out.println("Вы успешно вернули книгу: \""+title+"\"\nВ каталоге осталось: "+returnBook.getCopies());
        }
    }
    public List<Book> getAllBooks() {return catalogs;}

}









