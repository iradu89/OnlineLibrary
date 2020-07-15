package scoalainformala.ro.OnlineLibrary.dto;

import lombok.*;
import scoalainformala.ro.OnlineLibrary.domain.Address;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {

    private String name;

    @Email
    private String email;

    private String password;

    private Address address;
}
