package exception.handler;

import exception.AccountAlreadyExistException;
import exception.NegativeBalanceException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class NegativeBalanceExceptionHandler implements spark.ExceptionHandler<NegativeBalanceException> {

    @Override
    public void handle(NegativeBalanceException e, Request request, Response response) {
        response.status(HttpStatus.SC_BAD_REQUEST);
        response.body(e.getMessage());
    }
}
