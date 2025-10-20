package exceptions;

public class WrongLoanParamsException extends IllegalArgumentException {
    public WrongLoanParamsException(String message) {
        super(message);
    }
}
