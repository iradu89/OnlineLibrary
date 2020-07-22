package scoalainformala.ro.OnlineLibrary.dto;

import lombok.ToString;
import scoalainformala.ro.OnlineLibrary.domain.Address;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
public class UserEditDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @OneToOne
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
