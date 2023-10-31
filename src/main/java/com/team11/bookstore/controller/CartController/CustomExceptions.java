package com.team11.bookstore.controller.CartController;

public class CustomExceptions{
    public static class InvalidBookIDException extends Exception{
        public InvalidBookIDException(String message) {
            super(message);
        }
    }
    public static class BookAlreadyInTheCartException extends Exception{
        public BookAlreadyInTheCartException(String message) {
            super(message);
        }
    }

    public static class UnableToProcessOneOrMoreBooksException extends Exception{
        public UnableToProcessOneOrMoreBooksException(String message) {
            super(message);
        }
    }


}
