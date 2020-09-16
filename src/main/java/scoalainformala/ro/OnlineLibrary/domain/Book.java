package scoalainformala.ro.OnlineLibrary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@ToString
@Setter
@Getter
@Entity
public class Book {

    public Book() {
    }

    public Book(String author, String title, double price, String language, int quantity, int numOfPages, int recommendedAge, Genre genre) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.language = language;
        this.quantity = quantity;
        this.numOfPages = numOfPages;
        this.recommendedAge = recommendedAge;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private String author;
    private String title;
    private double price;
    private String language;
    private int quantity;
    private int numOfPages;
    private int recommendedAge;
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Set<BookReview> bookReviewSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, language);
    }
}
