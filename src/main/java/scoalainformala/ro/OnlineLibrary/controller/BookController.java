package scoalainformala.ro.OnlineLibrary.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import scoalainformala.ro.OnlineLibrary.domain.BookReview;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.service.BookService;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Log4j2
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/book/{id}", method = GET)
    public String book(@PathVariable UUID id, Model model, BookReview bookReview){
        BookDto bookDto = bookService.getBookByID(id);
        model.addAttribute("id", id);
        model.addAttribute("bookDto", bookDto);
//        model.addAttribute("bookDtoReceived", new BookDto());
        model.addAttribute("bookReview", bookReview);
        return "book";
    }

    @RequestMapping(value = "/addReview", method =  POST)
    public String review(@ModelAttribute(value = "bookDtoReceived") BookDto bookDtoReceived,
                         @ModelAttribute(value = "bookReview") BookReview bookReview){
        log.info(bookReview);
        log.info(bookDtoReceived);
        UUID id = bookService.addReviewToBook(bookReview);
        return "redirect:/book/" + id;
    }
}
