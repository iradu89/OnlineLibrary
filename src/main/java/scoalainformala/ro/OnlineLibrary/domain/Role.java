package scoalainformala.ro.OnlineLibrary.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN,
    BOOK_KEEPER,
    CLIENT;

    public String getAuthority() {
        return name();
    }
}


