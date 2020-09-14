package scoalainformala.ro.OnlineLibrary.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails authUser = userService.findByEmail(email);
        if(authUser == null) {
            throw new UsernameNotFoundException("Username" + email + "not found!");
        }
        return new User(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
    }
}
