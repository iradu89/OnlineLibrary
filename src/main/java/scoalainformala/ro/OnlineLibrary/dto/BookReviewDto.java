package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Setter
@Getter
@ToString
public class BookReviewDto {
    private UUID bookId;
    @NotNull()
    @NotBlank
    @Size(min = 5, max = 255, message = "The book review must be between {min} and {max} characters long")
    private String content;
}
