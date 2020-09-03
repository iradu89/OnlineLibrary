package scoalainformala.ro.OnlineLibrary.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;
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

    @RequestMapping(value = "/book/{bookId}", method = GET)
    public String book(@PathVariable UUID bookId, Model model, BookReviewDto bookReviewDto){
        BookDto bookDto = bookService.getBookByID(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("bookReviewDto", bookReviewDto);
        return "book";
    }

    @RequestMapping(value = "/addReview", method =  POST)
    public String review(@ModelAttribute(value = "bookReviewDto") BookReviewDto bookReviewDto){
        log.info(bookReviewDto);
        UUID id = bookService.addReviewToBook(bookReviewDto);
        return "redirect:/book/" + id;
    }
}
