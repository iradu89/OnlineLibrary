package scoalainformala.ro.OnlineLibrary.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    private String streetName;

    private int streetNumber;

    private String city;

    private String country;

    private int zipCode;

    public Address(String streetName, int streetNumber, String city, String country, int zipCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return streetName +
                ", no. " + streetNumber +
                ", " + city + ", " + country +
                ", " + zipCode;
    }
}
