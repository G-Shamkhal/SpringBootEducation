package ru.g_shamkhal.SpringBootEducation.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) { super(message); }
}
