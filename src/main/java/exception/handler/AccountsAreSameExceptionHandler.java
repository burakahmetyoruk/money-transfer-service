package exception.handler;

import exception.AccountsAreSameException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class AccountsAreSameExceptionHandler implements spark.ExceptionHandler<AccountsAreSameException> {

    @Override
    public void handle(AccountsAreSameException e, Request request, Response response) {
        response.status(HttpStatus.SC_BAD_REQUEST);
        response.body(e.getMessage());
    }
}