package scoalainformala.ro.OnlineLibrary.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.UUID;

@ToString
@Entity
@Setter
@Getter
public class BookReview {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;
    private String content;
    private String userName;
}
