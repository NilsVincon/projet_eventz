package com.epf.eventz.exception;

public class DAOException extends Exception {
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOException(String message) {
        super(message);
    }
}