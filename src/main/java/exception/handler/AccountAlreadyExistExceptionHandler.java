package exception.handler;

import exception.AccountAlreadyExistException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class AccountAlreadyExistExceptionHandler implements spark.ExceptionHandler<AccountAlreadyExistException> {

    @Override
    public void handle(AccountAlreadyExistException e, Request request, Response response) {
        response.status(HttpStatus.SC_CONFLICT);
        response.body(e.getMessage());
    }
}
