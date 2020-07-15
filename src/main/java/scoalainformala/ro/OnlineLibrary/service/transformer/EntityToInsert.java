package scoalainformala.ro.OnlineLibrary.service.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;

@Component
public class EntityToInsert implements Transformer<LibraryUser, UserInsertDto> {

    @Override
    public LibraryUser toEntity(UserInsertDto userInsertDto) {

        LibraryUser libraryUser = new LibraryUser();
        BeanUtils.copyProperties(userInsertDto, libraryUser);
        libraryUser.setUserRole(Role.USER);
        return libraryUser;
    }

    @Override
    public UserInsertDto toDto(LibraryUser libraryUser) {

        UserInsertDto userInsertDto = new UserInsertDto();
        BeanUtils.copyProperties(libraryUser, userInsertDto, "id", "role");
        return userInsertDto;
    }
}
