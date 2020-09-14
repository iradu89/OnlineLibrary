package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;
import scoalainformala.ro.OnlineLibrary.validation.ValidPassword;

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

    /**
     * Password must have 1 digit, 1 uppercase, 1 lowercase,
     * 1 special character (@#$%^&+=), no whitespaces, 8-20 characters
     */
    @NotBlank
    @ValidPassword
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
