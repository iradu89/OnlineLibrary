package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    public List<LibraryUser> findAll();

    public LibraryUser findByEmail(String email);

    public UserEditDto showUser(String email);

    public LibraryUser saveNewUser(UserInsertDto userInsertDto);

    public void saveExistingUser(UserEditDto userEditDto);

    public void deleteById(UUID id);
}
