package scoalainformala.ro.OnlineLibrary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class Acquisition {
    public Acquisition(Book book, LibraryUser libraryUser, @NotNull LocalDateTime dateTime, int quantity) {
        this.book = book;
        this.libraryUser = libraryUser;
        this.dateTime = dateTime;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "LIBRARYUSER_ID")
    private LibraryUser libraryUser;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private int quantity;
}
