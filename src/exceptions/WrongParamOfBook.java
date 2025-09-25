package exceptions;

public class WrongParamOfBook extends IllegalArgumentException {
    public WrongParamOfBook(String message) {
        super(message);
    }
}
