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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString

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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Min. 1 digit, 1 uppercase, 1 lowercase, 8-20 characters")
    @Size(min = 8, max = 20, message = "The password must be 8-20 characters long.")
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
