package scoalainformala.ro.OnlineLibrary.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;
    private String Author;
    private double price;
    private String language;
    private int quantity;
    private int numOfPages;
    private int recommendedAge;
    private Genre genre;
}
