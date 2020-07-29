package scoalainformala.ro.OnlineLibrary.service.impl;

import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.repository.BookRepository;
import scoalainformala.ro.OnlineLibrary.service.BookService;
import scoalainformala.ro.OnlineLibrary.transformer.BookToBookDtoTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookToBookDtoTransformer transformer;

    public BookServiceImpl(BookRepository bookRepository, BookToBookDtoTransformer transformer) {
        this.bookRepository = bookRepository;
        this.transformer = transformer;
    }

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllForBackend() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public BookDto getBookByID(UUID id) {
        Book book = bookRepository.findById(id).orElse(null);
        return transformer.transform(book);
    }
}
