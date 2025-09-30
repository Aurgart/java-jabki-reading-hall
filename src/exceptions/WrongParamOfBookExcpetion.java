package exceptions;

public class WrongParamOfBookExcpetion extends IllegalArgumentException {
    public WrongParamOfBookExcpetion(String message) {
        super(message);
    }
}
