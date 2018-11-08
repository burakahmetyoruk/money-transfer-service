package rest;

import exception.*;
import exception.handler.*;
import spark.Spark;

public class ExceptionHandlerInitializer {

    public static void initialize() {
        Spark.exception(AccountNotFoundException.class, new AccountNotFoundExceptionHandler());
        Spark.exception(AccountsAreSameException.class, new AccountsAreSameExceptionHandler());
        Spark.exception(AccountAlreadyExistException.class, new AccountAlreadyExistExceptionHandler());
        Spark.exception(NegativeBalanceException.class, new NegativeBalanceExceptionHandler());
        Spark.exception(RequestNotValidException.class, new RequestNotValidExceptionHandler());
        Spark.exception(Exception.class, new GeneralExceptionHandler());
    }
}