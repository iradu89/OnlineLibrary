package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;

import java.util.List;
import java.util.UUID;

@Validated
public interface BookService {
    @Transactional(rollbackFor = Exception.class)
    void add(Book book);

    @Transactional (readOnly = true)
    List<Book> getAllForBackend();

    @Transactional(rollbackFor = Exception.class)
    BookDto getBookByID(UUID id);

    @Transactional(rollbackFor = Exception.class)
    UUID addReviewToBook(BookReview bookReview);
}
