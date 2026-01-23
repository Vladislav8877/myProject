package project;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Publication> publications;
    Library() {
        this.publications = new ArrayList<>();
    }

    public void addPublication(Publication pub) {
        publications.add(pub);
        Publication.incrementCount();
    }

    public void listPublications() {
        for(Publication pub : publications) {
            System.out.println(pub.toString());
        }
    }

    public void searchByAuthor(String author) {
        boolean found = false;

        for(Publication pub : publications) {
            if(pub.getAuthor().equalsIgnoreCase(author)) {
                if(pub instanceof Printable p) {
                    p.printDetails();
                } else {
                    System.out.println(pub);
                }
                found = true;
            }
        }

        if(!found){
            System.out.println("Публикации автора \"" +author+ "\" не найдены!");
        }
    }
}
