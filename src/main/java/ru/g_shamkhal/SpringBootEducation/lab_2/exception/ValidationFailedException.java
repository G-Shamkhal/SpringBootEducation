package ru.g_shamkhal.SpringBootEducation.lab_2.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) { super(message); }
}
