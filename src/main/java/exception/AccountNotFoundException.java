package exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String accountName) {
        super("Account Not Found with Name: " + accountName);
    }

    public AccountNotFoundException(Long accountId) {
        super("Account Not Found with Id: " + accountId);
    }
}