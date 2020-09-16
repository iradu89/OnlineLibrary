package scoalainformala.ro.OnlineLibrary.exceptions;

import scoalainformala.ro.OnlineLibrary.domain.Book;

public class NotEnoughProductsInStockException extends Exception {
    public NotEnoughProductsInStockException(String message) {
        super(message);
    }

    public NotEnoughProductsInStockException(Book book) {
        super(String.format("Not enough copies of %s in stock. Only %d left", book.getTitle(), book.getQuantity()));
    }
}
