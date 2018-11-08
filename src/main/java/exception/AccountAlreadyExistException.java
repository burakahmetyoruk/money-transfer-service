package exception;

public class AccountAlreadyExistException extends RuntimeException {

    public AccountAlreadyExistException(String accountName) {
        super("Account Already Exist with Name: " + accountName);
    }
}