package error;

public class SqCalculatorException extends RuntimeException {

    public SqCalculatorException(Throwable cause) {
        super(cause);
    }

    public SqCalculatorException(String message) {
        super(message);
    }


}
