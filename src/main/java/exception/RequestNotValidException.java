package exception;

public class RequestNotValidException extends RuntimeException {

    public RequestNotValidException() {
        super("Request is not Valid");
    }
}