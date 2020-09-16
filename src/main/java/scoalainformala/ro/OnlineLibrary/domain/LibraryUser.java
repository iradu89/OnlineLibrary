package scoalainformala.ro.OnlineLibrary.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private boolean isActive;
}