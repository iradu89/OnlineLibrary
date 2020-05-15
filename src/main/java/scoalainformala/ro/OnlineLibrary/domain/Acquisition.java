package scoalainformala.ro.OnlineLibrary.domain;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Acquisition {
    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name="LIBRARYUSER_ID")
    private LibraryUser libraryUser;

    @NotNull
    private LocalDateTime dateTime;
}
