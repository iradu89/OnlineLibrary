package scoalainformala.ro.OnlineLibrary.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;

@Component
public class BookReviewTransformer {
    private final ModelMapper modelMapper;

    public BookReviewTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookReview transformBookReviewDtoToBookReview(BookReviewDto bookReviewDto) {
        BookReview bookReview = new BookReview();

        modelMapper.map(bookReviewDto, bookReview);
        return bookReview;
    }
}
