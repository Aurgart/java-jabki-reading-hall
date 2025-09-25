package exceptions;

public class WrongParamOfUser extends IllegalArgumentException {
    public WrongParamOfUser(String message) {
        super(message);
    }
}
