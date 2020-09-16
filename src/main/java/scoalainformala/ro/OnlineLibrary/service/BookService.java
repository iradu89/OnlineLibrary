package scoalainformala.ro.OnlineLibrary.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
public interface BookService {
    @Transactional(rollbackFor = Exception.class)
    void add(Book book);

    @Transactional(rollbackFor = Exception.class)
    BookDto add(BookDto bookdto);

    @Transactional(rollbackFor = Exception.class)
    void deleteById(UUID id);

    @Transactional(readOnly = true)
    List<Book> getAllForBackend();

    @Transactional(readOnly = true)
    List<BookDto> getAllForFrontEnd();

    @Transactional(rollbackFor = Exception.class)
    BookDto getBookByID(UUID id);

    @Transactional(rollbackFor = Exception.class)
    UUID addReviewToBook(@Valid BookReviewDto bookReviewDto);
}
