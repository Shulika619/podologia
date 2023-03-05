package dev.shulika.podologia.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
