package exception;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(String accountName) {
        super(accountName + " balance must not be negative!");
    }
}