package scoalainformala.ro.OnlineLibrary.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;

@Component
public class EntityVsInsert implements Transformer<LibraryUser, UserInsertDto> {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserInsertDto convertEntity(LibraryUser libraryUser) {

        UserInsertDto userInsertDto = new UserInsertDto();
        BeanUtils.copyProperties(libraryUser, userInsertDto, "id", "userRole");
        return userInsertDto;
    }

    @Override
    public LibraryUser convertDto(UserInsertDto userInsertDto) {

        LibraryUser toBeRegistered = new LibraryUser();
        BeanUtils.copyProperties(userInsertDto, toBeRegistered, "password", "confirmEmail", "confirmPassword");
        toBeRegistered.setPassword(encoder.encode(userInsertDto.getPassword()));
        return toBeRegistered;
    }
}
