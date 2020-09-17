package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;
import scoalainformala.ro.OnlineLibrary.validation.ValidPassword;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserInsertDto {

    @NotBlank
    @Size(min = 5, message = "Your full name must contain at least 4 letters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Only letters are valid.")
    private String name;

    @Email(message = "Please insert a valid email.")
    @NotBlank
    private String email;

    @NotBlank
    @ValidPassword(message = "invalid password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserInsertDto(@NotBlank String name, @Email String email, @NotBlank String password,
                         @Valid Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}