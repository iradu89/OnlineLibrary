package scoalainformala.ro.OnlineLibrary.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.exceptions.InvalidUserException;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;
import scoalainformala.ro.OnlineLibrary.service.UserService;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsEdit;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsInsert;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EntityVsEdit converter;

    private final EntityVsInsert konverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EntityVsEdit converter, EntityVsInsert konverter) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
        this.konverter = konverter;
    }

    public List<UserEditDto> getAll() {

        List<UserEditDto> finalList = new ArrayList<>();
        List<LibraryUser> libList = findAll();

        for (LibraryUser lib : libList) {
            if(lib.isActive()) {
                UserEditDto conv = converter.convertEntity(lib);
                finalList.add(conv);
            }
        }
        return finalList;
    }

    @Override
    public List<LibraryUser> findAll() {

        List<LibraryUser> list = userRepository.findAll();
        return list;
    }

    @Override
    public LibraryUser findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public UserEditDto showUser(String email) {

        LibraryUser libUser = userRepository.findByEmail(email);
        return converter.convertEntity(libUser);
    }

    @Override
    public LibraryUser saveNewUser(UserInsertDto userInsertDto) throws InvalidUserException {

        LibraryUser librUser = konverter.convertDto(userInsertDto);
        if (userRepository.existsByEmail(librUser.getEmail())) {
            throw new InvalidUserException("Username already in database");
        }
        librUser.setPassword(passwordEncoder.encode(librUser.getPassword()));
        librUser.setUserRole(Role.CLIENT);
        librUser.setActive(true);
        return userRepository.save(librUser);
    }

    @Override
    public UserEditDto saveUserEdit(UserEditDto userEditDto) {

        LibraryUser libU = userRepository.findByEmail(userEditDto.getEmail());
        BeanUtils.copyProperties(userEditDto, libU, "id", "email", "role");
        libU.setPassword(passwordEncoder.encode(libU.getPassword()));
        userRepository.save(libU);
        return userEditDto;
    }

    @Override
    public void inactivateUser(String email) {
        LibraryUser toBeInactivated = userRepository.findByEmail(email);
        toBeInactivated.setActive(false);
        userRepository.save(toBeInactivated);
    }

    @Override
    public void activateUser(String email) {
        LibraryUser inactiveUser = userRepository.findByEmail(email);
        inactiveUser.setActive(true);
        userRepository.save(inactiveUser);
    }
}

