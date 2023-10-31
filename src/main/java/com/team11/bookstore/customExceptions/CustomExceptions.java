package com.team11.bookstore.customExceptions;

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

    public static class EmptyCartException extends Exception{
        public EmptyCartException(String message) {
            super(message);
        }
    }

    public static class UserDoesNotExistException extends Exception{
        public UserDoesNotExistException(String message) {
            super(message);
        }
    }


}
