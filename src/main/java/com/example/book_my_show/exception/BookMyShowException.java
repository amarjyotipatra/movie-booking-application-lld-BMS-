package com.example.book_my_show.exception;

public class BookMyShowException extends RuntimeException {
    
    public BookMyShowException(String message) {
        super(message);
    }
    
    public BookMyShowException(String message, Throwable cause) {
        super(message, cause);
    }
}
