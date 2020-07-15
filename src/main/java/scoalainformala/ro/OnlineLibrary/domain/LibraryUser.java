package scoalainformala.ro.OnlineLibrary.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LibraryUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Role userRole;

    @OneToOne
    private Address address;
}
