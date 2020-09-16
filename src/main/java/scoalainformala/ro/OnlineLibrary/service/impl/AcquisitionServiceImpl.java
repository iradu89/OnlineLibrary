package scoalainformala.ro.OnlineLibrary.service.impl;

import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.Acquisition;
import scoalainformala.ro.OnlineLibrary.domain.Book;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.AcquisitionDto;
import scoalainformala.ro.OnlineLibrary.exceptions.NotEnoughProductsInStockException;
import scoalainformala.ro.OnlineLibrary.repository.AcquisitionRepository;
import scoalainformala.ro.OnlineLibrary.repository.BookRepository;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;
import scoalainformala.ro.OnlineLibrary.service.AcquisitionService;

import java.time.LocalDateTime;

@Service
public class AcquisitionServiceImpl implements AcquisitionService {
    private final AcquisitionRepository acquisitionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public AcquisitionServiceImpl(AcquisitionRepository acquisitionRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.acquisitionRepository = acquisitionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Acquisition add(AcquisitionDto acquisitionDto) throws NotEnoughProductsInStockException {
        LibraryUser libUser = userRepository.findByEmail(acquisitionDto.getLibUserEmail());
        Book book = bookRepository.findById(acquisitionDto.getBookId()).orElseThrow();
        if (acquisitionDto.getQuantity() <= 0){
            throw new NotEnoughProductsInStockException("Please select a valid number, 0 is not a valid number");
        }
        if (book.getQuantity() - acquisitionDto.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - acquisitionDto.getQuantity());
            bookRepository.save(book);
        } else throw new NotEnoughProductsInStockException(book);
        Acquisition acquisition = new Acquisition(book, libUser, LocalDateTime.now(), acquisitionDto.getQuantity());
        return acquisitionRepository.save(acquisition);
    }
}
