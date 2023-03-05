package dev.shulika.podologia.exception;

public class ServiceBusinessException extends RuntimeException{
    public ServiceBusinessException() {
        super();
    }

    public ServiceBusinessException(String message) {
        super(message);
    }
}
