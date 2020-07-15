package scoalainformala.ro.OnlineLibrary.service.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.service.UserService;

@Component
public class EntityToEdit implements Transformer<LibraryUser, UserEditDto> {

    @Autowired
    private UserService userService;

    @Override
    public LibraryUser toEntity(UserEditDto userEditDto) {

        LibraryUser existingUser = userService.findByEmail(userEditDto.getEmail());
        existingUser.setName(userEditDto.getName());
        existingUser.setPassword(userEditDto.getPassword());
        existingUser.setAddress(userEditDto.getAddress());
        return existingUser;
    }

    @Override
    public UserEditDto toDto(LibraryUser libraryUser) {

        UserEditDto userEditDto = new UserEditDto();
        BeanUtils.copyProperties(libraryUser, userEditDto, "id", "email", "role");
        return userEditDto;
    }
}

