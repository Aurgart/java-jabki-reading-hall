package exceptions;

public class BookQuantityException extends IllegalArgumentException {
    public BookQuantityException(String message) {
        super(message);
    }
}
