package scoalainformala.ro.OnlineLibrary.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.service.UserService;

@Component
public class EntityVsEdit implements Transformer<LibraryUser, UserEditDto> {

    UserService userService;

    @Override
    public UserEditDto convertEntity(LibraryUser libraryUser) {

        UserEditDto userEdit = new UserEditDto();
        BeanUtils.copyProperties(libraryUser, userEdit, "id", "role");
        return userEdit;
    }

    @Override
    public LibraryUser convertDto(UserEditDto userEditDto) {

        LibraryUser existingUser = userService.findByEmail(userEditDto.getEmail());
        BeanUtils.copyProperties(userEditDto, existingUser, "id", "email", "role");
        return existingUser;
    }
}
