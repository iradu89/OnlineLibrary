package scoalainformala.ro.OnlineLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, UUID> {

    LibraryUser findByEmail(String email);

}
