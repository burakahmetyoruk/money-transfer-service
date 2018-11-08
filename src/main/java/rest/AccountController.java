package rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import exception.RequestNotValidException;
import model.account.AccountRequest;
import model.account.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AccountService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.sql.SQLException;

public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private static final AccountController accountController = new AccountController();

    public static AccountController of() {
        return accountController;
    }

    AccountResponse create(Request request, Response response) throws SQLException {
        AccountRequest accountRequest = retrieveRequest(request);
        return accountService.create(accountRequest);
    }

    AccountResponse retrieve(String accountName) {
        return accountService.retrieve(accountName);
    }

    private AccountRequest retrieveRequest(Request request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(request.body(), AccountRequest.class);
        } catch (IOException e) {
            logger.error("Error Occurred When Parsing Request", e);
            throw new RequestNotValidException();
        }
    }

    private AccountController() {
        this.accountService = AccountService.of();
    }
}