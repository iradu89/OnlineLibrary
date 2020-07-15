package scoalainformala.ro.OnlineLibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LibraryUser libraryUser = userRepository.findByEmail(email);
        if(libraryUser == null) {
            throw new UsernameNotFoundException("Invalid username/ password.");
        }
        return new User(
                libraryUser.getEmail(),
                libraryUser.getPassword(),
                mapRolesToAuthorities(Collections.singleton(libraryUser.getUserRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
