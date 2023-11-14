package com.team11.bookstore.customExceptions;

public class CustomExceptions{
    public static class InvalidBookIDException extends Exception{
        public InvalidBookIDException(String message) {
            super(message);
        }
    }
    public static class BookNotInCartException extends RuntimeException{
        public BookNotInCartException(String message) {
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

    public static class MissingCreditCardFieldsException extends Exception{
        public MissingCreditCardFieldsException(String message) {
            super(message);
        }
    }

    public static class UsernameAlreadyExistsException extends Exception{
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class CannotChangeEmailException extends Exception{
        public CannotChangeEmailException(String message) {
            super(message);
        }
    }

    public static class MissingSignUpFieldsException extends Exception{
        public MissingSignUpFieldsException(String message) {
            super(message);
        }
    }

}
