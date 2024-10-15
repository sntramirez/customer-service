package com.dev.customerservice.exceptions;

public class PersonaNoDisponibleException extends RuntimeException {
    public PersonaNoDisponibleException(String message) {
        super(message);
    }
}
