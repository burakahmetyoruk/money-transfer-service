package exception.handler;

import exception.RequestNotValidException;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class RequestNotValidExceptionHandler implements spark.ExceptionHandler<RequestNotValidException> {

    @Override
    public void handle(RequestNotValidException e, Request request, Response response) {
        response.status(HttpStatus.SC_BAD_REQUEST);
        response.body(e.getMessage());
    }
}
