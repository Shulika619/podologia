package dev.shulika.podologia.exception;

public class ObjectNotFoundException extends RuntimeException{
    private String field;

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String field,String message) {
        super(message);
        this.field=field;
    }

    public String getField() {
        return field;
    }
}
