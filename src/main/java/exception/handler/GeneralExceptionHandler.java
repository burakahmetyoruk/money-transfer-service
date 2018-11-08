package exception.handler;

import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class GeneralExceptionHandler implements spark.ExceptionHandler<Exception> {

    @Override
    public void handle(Exception e, Request request, Response response) {
        response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.body("Error Occurred When Processing Request");
    }
}
