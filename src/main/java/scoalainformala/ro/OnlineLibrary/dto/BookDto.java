package scoalainformala.ro.OnlineLibrary.dto;

import lombok.*;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.domain.Genre;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private UUID id;

    @NotNull()
    @NotBlank
    @Size(min = 2, max = 50, message = "The Author name must be between {min} and {max} characters long")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid author name")
    private String author;

    @NotNull()
    @NotBlank
    @Size(min = 2, max = 150, message = "The book title must be between {min} and {max} characters long")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid title name")
    private String title;

    @NotNull
    @Min(value = 1, message = "The price must be greater or equal to 1")
    @Max(value = 10000, message = "The price cannot be greater than 10000")
    private double price;

    @NotNull()
    @NotBlank
    @Size(min = 2, max = 150, message = "The language must be between {min} and {max} characters long")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid language name")
    private String language;

    @NotNull
    @Min(value = 1, message = "The quantity must be greater or equal to 1")
    @Max(value = 5000, message = "The quantity cannot be greater than 5000")
    private int quantity;

    @NotNull
    @Min(value = 1, message = "The number of pages must be greater or equal to 1")
    @Max(value = 8000, message = "The number of pages cannot be greater than 8000")
    private int numOfPages;

    @NotNull
    @Min(value = 3, message = "The recommended age must be greater or equal to 3")
    @Max(value = 18, message = "The recommended age cannot be greater than 18")
    private int recommendedAge;

    private Genre genre;
    private Set<BookReview> bookReviewSet;
}
