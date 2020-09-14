package scoalainformala.ro.OnlineLibrary.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
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
import java.util.UUID;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    private EntityVsEdit converter;

    @Autowired
    private EntityVsInsert konverter;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<UserEditDto> getAll() {

        List<UserEditDto> finalList = new ArrayList<>();
        List<LibraryUser> libList = findAll();

        for (LibraryUser lib : libList) {
            UserEditDto conv = converter.convertEntity(lib);
            finalList.add(conv);
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

        LibraryUser existingUser = userRepository.findByEmail(email);
        return existingUser;
    }

    @Override
    public UserEditDto showUser(String email) {

        UserEditDto servedDto;
        LibraryUser libUser = userRepository.findByEmail(email);
        servedDto = converter.convertEntity(libUser);
        return servedDto;
    }

    @SneakyThrows
    @Override
    public LibraryUser saveNewUser(UserInsertDto userInsertDto) {
        LibraryUser existingUser = userRepository.findByEmail(userInsertDto.getEmail());
        if (existingUser != null) {
            throw new InvalidUserException("User already in database!");
        }
        LibraryUser librUser = konverter.convertDto(userInsertDto);
        librUser.setPassword(passwordEncoder.encode(librUser.getPassword()));
        librUser.setUserRole(Role.CLIENT);
        librUser.setActive(true);
        userRepository.save(librUser);
        return librUser;
    }

    @Override
    public UserEditDto saveUserEdit(UserEditDto userEditDto) {

        LibraryUser libU = converter.convertDto(userEditDto);
        userRepository.save(libU);
        return converter.convertEntity(libU);
    }

    @Override
    public void deleteById(UUID id) {
//TODO to be implemented
    }
}
