package scoalainformala.ro.OnlineLibrary.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import scoalainformala.ro.OnlineLibrary.dto.AcquisitionDto;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.service.AcquisitionService;
import scoalainformala.ro.OnlineLibrary.service.BookService;
import scoalainformala.ro.OnlineLibrary.service.UserService;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Log4j2
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final AcquisitionService acquisitionService;

    public BookController(BookService bookService, UserService userService, AcquisitionService acquisitionService) {
        this.bookService = bookService;
        this.userService = userService;
        this.acquisitionService = acquisitionService;
    }

    @RequestMapping(value = "/book/{bookId}", method = GET)
    public String book(@PathVariable UUID bookId, Model model, BookReviewDto bookReviewDto, BookDto bookDtoT) {
        BookDto bookDto = bookService.getBookByID(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("bookReviewDto", bookReviewDto);
        return "book";
    }

    @RequestMapping(value = "/addReview", method = POST)
    public String review(@Valid @ModelAttribute(value = "bookReviewDto") BookReviewDto bookReviewDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            BookDto bookDto = bookService.getBookByID(bookReviewDto.getBookId());
            model.addAttribute("bookId", bookReviewDto.getBookId());
            model.addAttribute("bookDto", bookDto);
            model.addAttribute("bookReviewDto", bookReviewDto);
            log.info(bindingResult);
            return "book";
        }
        log.info(bookReviewDto);
        UUID id = bookService.addReviewToBook(bookReviewDto);
        return "redirect:/book/" + id;
    }

    @RequestMapping(value = "/buyBook", method = GET)
    public String buyBook(Model model,
                          @ModelAttribute(value = "bookDto") BookDto bookDto,
                          UserEditDto libraryUser,
                          AcquisitionDto acquisitionDto) {
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        libraryUser = userService.showUser(loggedUser.getName());
        model.addAttribute("libraryUser", libraryUser);
        bookDto = bookService.getBookByID(bookDto.getId());
        model.addAttribute("bookDto", bookDto);
        model.addAttribute(acquisitionDto);
        return "buyBookForm";
    }

    @RequestMapping(value = "confirmPurchase", method = POST)
    public String confirmPurchase(@ModelAttribute(value ="acquisitionDto") AcquisitionDto acquisitionDto, Model model) {
        log.info(acquisitionDto);
        log.info(acquisitionService.add(acquisitionDto));
        return "redirect:/book/" + acquisitionDto.getBookId();
    }
}
