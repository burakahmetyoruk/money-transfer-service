package rest;


import model.account.AccountRequest;
import model.account.AccountResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AccountService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class AccountController implements BaseController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private static final AccountController accountController = new AccountController();

    public static AccountController of() {
        return accountController;
    }

    AccountResponse create(Request request, Response response) throws SQLException {
        AccountRequest accountRequest = retrieveRequest(request, AccountRequest.class);
        AccountResponse accountResponse = accountService.create(accountRequest);
        response.status(HttpStatus.SC_CREATED);
        return accountResponse;
    }

    AccountResponse retrieve(String accountName) {
        return accountService.retrieve(accountName);
    }

    private AccountController() {
        this.accountService = AccountService.of();
    }
}