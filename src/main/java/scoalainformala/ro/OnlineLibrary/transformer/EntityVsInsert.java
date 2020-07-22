package scoalainformala.ro.OnlineLibrary.transformer;

import org.springframework.beans.BeanUtils;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.domain.Role;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;

public class EntityVsInsert implements Transformer<LibraryUser, UserInsertDto> {

    @Override
    public UserInsertDto convertEntity(LibraryUser libraryUser) {
        return null;
    }

    @Override
    public LibraryUser convertDto(UserInsertDto userInsertDto) {

        LibraryUser toBeRegistered = new LibraryUser();
        BeanUtils.copyProperties(userInsertDto, toBeRegistered);
        toBeRegistered.setUserRole(Role.CLIENT);
        return toBeRegistered;
    }
}
