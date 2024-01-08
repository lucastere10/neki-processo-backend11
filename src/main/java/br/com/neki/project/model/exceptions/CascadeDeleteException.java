package br.com.neki.project.model.exceptions;

public class CascadeDeleteException extends RuntimeException {
    public CascadeDeleteException(String message) {
        super(message);
    }
}
