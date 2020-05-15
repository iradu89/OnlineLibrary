package scoalainformala.ro.OnlineLibrary.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Address {
    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;

    @NotBlank
    private String streetName;

    private int streetNumber;
    private String city;
    private String country;

    @NotNull
    private int zipCode;
}
