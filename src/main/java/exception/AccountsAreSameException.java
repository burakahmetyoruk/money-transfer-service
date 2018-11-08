package exception;

public class AccountsAreSameException extends RuntimeException {

    public AccountsAreSameException() {
        super("Transferrer Account and Transferred Account must not be same");
    }
}