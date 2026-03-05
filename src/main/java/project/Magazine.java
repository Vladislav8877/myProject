package project;

import java.util.Objects;

public class Magazine extends Publication implements Printable {

    private int issueNumber;

    Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        setIssueNumber(issueNumber);
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber < 0) {
            throw new IllegalArgumentException("Поле \"Номер выпуска\" - не может быть пустым.");
        }
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return String.format("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nНомер выпуска: %d%n",
                getTitle(), getAuthor(), getYear(), issueNumber);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Magazine magazine = (Magazine) obj;
        return issueNumber == magazine.issueNumber && (Objects.equals(getAuthor(), magazine.getAuthor()));
    }
    @Override
    public int hashCode() {
        int result = getAuthor() != null ? getAuthor().hashCode() : 0;
        result = 31 * result + issueNumber;
        return result;
    }

    @Override
    public void printDetails() {
        System.out.printf("Название публикации: %s%nАвтор публикации: %s%nГод выпуска: %d%nНомер Выпуска: %d%n",
                getTitle(), getAuthor(), getYear(), getIssueNumber());
    }

    public int getIssueNumber() {return issueNumber;}
    public String getType() {return "Magazine";}
}