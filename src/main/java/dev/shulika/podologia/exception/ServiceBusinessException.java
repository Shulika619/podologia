package dev.shulika.podologia.exception;

public class ServiceBusinessException extends RuntimeException {
    private String field;

    public ServiceBusinessException() {
        super();
    }

    public ServiceBusinessException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
