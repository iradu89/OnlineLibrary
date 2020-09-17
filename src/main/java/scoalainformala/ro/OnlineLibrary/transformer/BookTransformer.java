package scoalainformala.ro.OnlineLibrary.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;

@Component
public class BookTransformer {

    private final ModelMapper modelMapper;

    public BookTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDto transformBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        modelMapper.map(book, bookDto);
        return bookDto;
    }

    public Book transformBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        modelMapper.map(bookDto, book);
        return book;
    }
}
