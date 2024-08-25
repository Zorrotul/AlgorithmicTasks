package error;

public class CounterException extends RuntimeException {

    public CounterException(Throwable cause) {
        super(cause);
    }

    public CounterException(String message) {
        super(message);
    }


}
