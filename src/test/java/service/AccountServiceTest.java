package service;

import domain.H2DataBaseService;
import exception.AccountNotFoundException;
import model.account.AccountRequest;
import model.account.AccountResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService accountService = AccountService.of();

    @Before
    public void setup() {
        H2DataBaseService.init();
    }

    @Test
    public void should_create_account_with_initial_balance() throws SQLException {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountName("accountName");

        AccountResponse accountResponse = accountService.create(accountRequest);

        assertNotNull(accountResponse);
        assertNotNull(accountResponse.getOperationResult());
        assertEquals(0, accountResponse.getOperationResult().getReturnCode().intValue());
    }

    @Test(expected = AccountNotFoundException.class)
    public void should_throw_account_not_found_exception_when_account_does_not_exist() throws SQLException {
        String transferrerAccountName = "transferrerAccount";

        accountService.retrieve(transferrerAccountName);
    }

    @After
    public void tearDown() {
        H2DataBaseService.drop();
    }
}