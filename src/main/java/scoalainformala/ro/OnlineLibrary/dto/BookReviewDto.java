package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Setter
@Getter
@ToString
public class BookReviewDto {
    private UUID bookId;
    @NotNull()
    @NotBlank
    @Size(min = 5, max = 1000, message = "The book review must be between {min} and {max} characters long")
    private String content;

    @NotNull()
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$", message = "Invalid Username")
    @Size(min = 2, max = 20, message = "The Name must be between {min} and {max} characters long")
    private String userName;
}
