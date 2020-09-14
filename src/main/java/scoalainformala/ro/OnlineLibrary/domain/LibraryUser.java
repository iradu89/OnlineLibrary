package scoalainformala.ro.OnlineLibrary.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
        import javax.validation.constraints.Email;
        import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUser implements UserDetails {
    @Id
    @GeneratedValue(generator="system-uuid")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getUserRole());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}