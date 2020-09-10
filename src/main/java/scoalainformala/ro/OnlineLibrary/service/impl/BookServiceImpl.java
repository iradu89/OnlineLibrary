package scoalainformala.ro.OnlineLibrary.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;
import scoalainformala.ro.OnlineLibrary.repository.BookRepository;
import scoalainformala.ro.OnlineLibrary.service.BookService;
import scoalainformala.ro.OnlineLibrary.transformer.BookReviewTransformer;
import scoalainformala.ro.OnlineLibrary.transformer.BookTransformer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookTransformer transformer;
    private final BookReviewTransformer reviewTransformer;

    public BookServiceImpl(BookRepository bookRepository, BookTransformer transformer, BookReviewTransformer reviewTransformer) {
        this.bookRepository = bookRepository;
        this.transformer = transformer;
        this.reviewTransformer = reviewTransformer;
    }

    //Used for Backend
    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    //Used for Frontend
    @Override
    public BookDto add(BookDto bookdto) {
        return transformer.transformBookToBookDto(bookRepository.save(transformer.transformBookDtoToBook(bookdto)));
    }

    @Override
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllForBackend() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public List<BookDto> getAllForFrontEnd() {
        List<BookDto> books = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            books.add(transformer.transformBookToBookDto(book));
        });
        return books;
    }

    @Override
    public BookDto getBookByID(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);
        return transformer.transformBookToBookDto(book);
    }

    @Override
    public UUID addReviewToBook(@Valid BookReviewDto bookReviewDto) {
        BookDto bookDtoToUpdate = getBookByID(bookReviewDto.getBookId());

        BookReview bookReview = reviewTransformer.transformBookReviewDtoToBookReview(bookReviewDto);
        bookDtoToUpdate.getBookReviewSet().add(bookReview);

        Book bookToUpdate = transformer.transformBookDtoToBook(bookDtoToUpdate);
        bookRepository.save(bookToUpdate);
        return bookToUpdate.getId();
    }
}
