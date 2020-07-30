package scoalainformala.ro.OnlineLibrary.dto;

import lombok.*;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.domain.Genre;

import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private UUID id;
    private String author;
    private String title;
    private double price;
    private String language;
    private int quantity;
    private int numOfPages;
    private int recommendedAge;
    private Genre genre;
    private Set<BookReview> bookReviewSet;
}
