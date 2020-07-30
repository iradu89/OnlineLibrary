package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserInsertDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserInsertDto(@NotBlank String name, @Email String email, @NotBlank String password,
                         Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
