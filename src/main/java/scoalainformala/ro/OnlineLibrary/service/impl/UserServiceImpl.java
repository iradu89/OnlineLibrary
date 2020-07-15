package scoalainformala.ro.OnlineLibrary.service.impl;

import scoalainformala.ro.OnlineLibrary.service.UserService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;
import scoalainformala.ro.OnlineLibrary.service.transformer.EntityToEdit;


import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EntityToEdit entityToEdit;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        LibraryUser libraryUser = userRepository.findByEmail(email);
        if (libraryUser == null) {
            throw new UsernameNotFoundException("Invalid username / password.");
        }
        return new User(libraryUser.getEmail(),
                libraryUser.getPassword(),
                mapRolesToAuthorities(Collections.singleton(libraryUser.getUserRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public LibraryUser createUser(UserInsertDto userInsertDto) {

        LibraryUser libraryUser = new LibraryUser();
        BeanUtils.copyProperties(userInsertDto, libraryUser, "confirmEmail", "confirmPassword");
        libraryUser.setUserRole(Role.USER);
        log.info(userInsertDto);
        log.info(libraryUser);
        return userRepository.save(libraryUser);
    }

    @Override
    public UserEditDto readUser(String email) {
        LibraryUser libUser = userRepository.findByEmail(email);
        UserEditDto userEditDto = entityToEdit.toDto(libUser);
        return userEditDto;
    }

    @Override
    public LibraryUser updateUser(UserEditDto userEditDto) {

        LibraryUser libraryUser = findByEmail(userEditDto.getEmail());
        libraryUser.setName(userEditDto.getName());
        libraryUser.setPassword(userEditDto.getPassword());
        libraryUser.setAddress(userEditDto.getAddress());
        return userRepository.save(libraryUser);
    }

    @Override
    public LibraryUser deleteUser(UUID id) {

        //TODO after deciding if delete or hide user
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public LibraryUser findByEmail(String email) {

        LibraryUser libraryUser = null;
        try {
            libraryUser = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return libraryUser;
    }
}

