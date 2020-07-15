package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;
import scoalainformala.ro.OnlineLibrary.service.constraints.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserInsertDto {

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @NotEmpty
    private String password;

    @NotBlank
    private String confirmPassword;

    private Address address;
}
