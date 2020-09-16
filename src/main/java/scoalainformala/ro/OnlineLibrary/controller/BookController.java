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
import org.springframework.web.bind.annotation.RequestParam;
import scoalainformala.ro.OnlineLibrary.domain.Genre;
import scoalainformala.ro.OnlineLibrary.dto.AcquisitionDto;
import scoalainformala.ro.OnlineLibrary.dto.BookDto;
import scoalainformala.ro.OnlineLibrary.dto.BookReviewDto;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.exceptions.NotEnoughProductsInStockException;
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
        return "books/book";
    }

    @RequestMapping(value = "/addReview", method = POST)
    public String review(@Valid @ModelAttribute(value = "bookReviewDto") BookReviewDto bookReviewDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            BookDto bookDto = bookService.getBookByID(bookReviewDto.getBookId());
            model.addAttribute("bookId", bookReviewDto.getBookId());
            model.addAttribute("bookDto", bookDto);
            model.addAttribute("bookReviewDto", bookReviewDto);
            return "books/book";
        }
        log.info(bookReviewDto);
        UUID id = bookService.addReviewToBook(bookReviewDto);
        return "redirect:/book/" + id;
    }

    @RequestMapping(value = "/buyBook", method = GET)
    public String buyBook(Model model,
                          @ModelAttribute(value = "bookDto") BookDto bookDto, UserEditDto libraryUser,
                          AcquisitionDto acquisitionDto) {
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        libraryUser = userService.showUser(loggedUser.getName());
        model.addAttribute("libraryUser", libraryUser);
        bookDto = bookService.getBookByID(bookDto.getId());
        model.addAttribute("bookDto", bookDto);
        model.addAttribute(acquisitionDto);
        return "books/buyBookForm";
    }


    @RequestMapping(value = "confirmPurchase", method = POST)
    public String confirmPurchase(@ModelAttribute(value = "acquisitionDto") AcquisitionDto acquisitionDto, Model model) {
        try {
            log.info(acquisitionService.add(acquisitionDto));
        } catch (NotEnoughProductsInStockException e) {
            model.addAttribute("outOfStockMessage", e.getMessage());
            BookDto bookdto = bookService.getBookByID(acquisitionDto.getBookId());
            Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
            UserEditDto libraryUser = userService.showUser(loggedUser.getName());
            return buyBook(model, bookdto, libraryUser, new AcquisitionDto());
        }
        return "redirect:/book/" + acquisitionDto.getBookId();
    }

    @RequestMapping(value = "/listBooks", method = GET)
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllForFrontEnd());
        return "books/list-books";
    }

    @RequestMapping(value = "/showFormForAddBook", method = GET)
    public String showBookForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("genreList", Genre.values());
        return "books/book-form";
    }

    @RequestMapping(value = "/addBook", method = POST)
    public String addBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookDto", bookDto);
            model.addAttribute("genreList", Genre.values());
            return "books/book-form";
        }
        bookService.add(bookDto);
        return "redirect:/listBooks";
    }

    @RequestMapping(value = "/showFormForUpdateBook", method = GET)
    public String showBookFormForUpdate(@RequestParam("bookId") UUID bookId, Model model) {
        BookDto bookDto = bookService.getBookByID(bookId);
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("genreList", Genre.values());
        return "books/book-form";
    }

    @RequestMapping(value = "/deleteBook", method = GET)
    public String deleteBook(@RequestParam("bookId") UUID bookId) {
        bookService.deleteById(bookId);
        return "redirect:/listBooks";
    }
}
