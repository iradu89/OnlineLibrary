package scoalainformala.ro.OnlineLibrary.repository;

import org.springframework.data.repository.CrudRepository;
import scoalainformala.ro.OnlineLibrary.domain.Book;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
}
