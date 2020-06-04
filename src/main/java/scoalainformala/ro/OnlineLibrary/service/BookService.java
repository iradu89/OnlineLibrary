package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import scoalainformala.ro.OnlineLibrary.domain.Book;

import java.util.List;

@Validated
public interface BookService {
    @Transactional(rollbackFor = Exception.class)
    void add(Book book);

    @Transactional (readOnly = true)
    List<Book> getAllForBackend();
}
