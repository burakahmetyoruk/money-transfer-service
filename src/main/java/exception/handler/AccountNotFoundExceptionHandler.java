package exception.handler;

import exception.AccountNotFoundException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class AccountNotFoundExceptionHandler implements spark.ExceptionHandler<AccountNotFoundException> {

    @Override
    public void handle(AccountNotFoundException e, Request request, Response response) {
        response.status(HttpStatus.SC_NOT_FOUND);
        response.body(e.getMessage());
    }
}
