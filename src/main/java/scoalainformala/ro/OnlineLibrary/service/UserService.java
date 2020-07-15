package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;

import java.util.UUID;

@Service
public interface UserService {

    LibraryUser createUser(UserInsertDto userInsertDto);

    UserEditDto readUser(String email);

    LibraryUser updateUser(UserEditDto userEditDto);

    LibraryUser deleteUser(UUID id);

    LibraryUser findByEmail(String email);
}
