package exceptions;

public class WrongParamOfBookException extends IllegalArgumentException {
    public WrongParamOfBookException(String message) {
        super(message);
    }
}
