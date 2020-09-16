package scoalainformala.ro.OnlineLibrary.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isActive;
    private List<SimpleGrantedAuthority> authority;


    public MyUserDetails(LibraryUser libUser) {
        this.username = libUser.getEmail();
        this.password = libUser.getPassword();
        this.isActive = libUser.isActive();
        authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(libUser.getUserRole().toString()));
        authority.forEach(System.out::println);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}
