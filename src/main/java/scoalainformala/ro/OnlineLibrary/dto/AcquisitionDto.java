package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AcquisitionDto {
    private UUID bookId;
    private String libUserEmail;
    private int quantity;
}
