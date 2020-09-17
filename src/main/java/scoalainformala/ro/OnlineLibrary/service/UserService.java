package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.exceptions.InvalidUserException;

import java.util.List;

@Service
public interface UserService {

    public List<UserEditDto> getAll();

    public LibraryUser findByEmail(String email);

    public UserEditDto showUser(String email);

    public LibraryUser saveNewUser(UserInsertDto userInsertDto) throws InvalidUserException, InvalidUserException;

    public UserEditDto saveUserEdit(UserEditDto userEditDto);

    public void inactivateUser(String email);

    public void activateUser(String email);

    public List<LibraryUser> findAll();
}
