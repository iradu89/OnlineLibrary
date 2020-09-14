package scoalainformala.ro.OnlineLibrary.domain;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public enum Role implements GrantedAuthority {

    ADMIN,
    BOOK_KEEPER,
    CLIENT;


    @Override
    public String getAuthority() {
        return name();
    }
}


