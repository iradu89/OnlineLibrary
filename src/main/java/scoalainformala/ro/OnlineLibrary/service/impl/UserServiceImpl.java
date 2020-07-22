package scoalainformala.ro.OnlineLibrary.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;
import scoalainformala.ro.OnlineLibrary.service.UserService;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsEdit;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsInsert;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private EntityVsEdit converter;

    private EntityVsInsert konverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<LibraryUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public LibraryUser findByEmail(String email) {
        return null;
    }

    @Override
    public UserEditDto showUser(String email) {
        UserEditDto servedDto = new UserEditDto();
        LibraryUser libUser = userRepository.findByEmail(email);
        if (libUser != null) {
            servedDto = converter.convertEntity(libUser);
        }
        return servedDto;
    }

    @Override
    public LibraryUser saveNewUser(UserInsertDto userInsertDto) {
        LibraryUser libUser;
        libUser = konverter.convertDto(userInsertDto);
        userRepository.save(libUser);

        return libUser;
    }

    @Override
    public void saveExistingUser(UserEditDto userEditDto) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
