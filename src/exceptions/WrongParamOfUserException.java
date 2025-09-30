package exceptions;

public class WrongParamOfUserException extends IllegalArgumentException {
    public WrongParamOfUserException(String message) {
        super(message);
    }
}
