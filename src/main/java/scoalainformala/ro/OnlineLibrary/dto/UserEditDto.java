package scoalainformala.ro.OnlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;
import scoalainformala.ro.OnlineLibrary.validation.ValidPassword;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class UserEditDto {

    @NotBlank
    @Size(min = 5, message = "Your full name must contain at least 4 letters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Only letters are valid.")
    private String name;

    @Email
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserEditDto(@NotBlank String name, @Email String email, @NotBlank String password, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public UserEditDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
