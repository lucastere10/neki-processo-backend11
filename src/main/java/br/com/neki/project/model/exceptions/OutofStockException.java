package br.com.neki.project.model.exceptions;

public class OutofStockException extends RuntimeException {

    public static final long serialVersion = 1L;

    public OutofStockException(String message) {
        super(message);
    }
}
