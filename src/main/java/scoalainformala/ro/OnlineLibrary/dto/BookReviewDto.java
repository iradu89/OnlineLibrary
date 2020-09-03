package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class BookReviewDto {
    private UUID bookId;
    private String content;
    private String userName;
}
