package service;


import domain.entity.Account;
import domain.repository.AccountRepository;
import model.transfer.TransferRequest;
import model.transfer.TransferResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransferServiceTest {

    private TransferService transferService = TransferService.of();
    private AccountRepository accountRepository = AccountRepository.of();

    @Before
    public void setup() {
        String transferrerAccountName = "transferrerAccount";
        String transferredAccountName = "transferredAccount";
        Account transferrerAccount = new Account();
        transferrerAccount.setName(transferrerAccountName);
        transferrerAccount.setBalance(BigDecimal.valueOf(30));

        Account transferredAccount = new Account();
        transferredAccount.setName(transferredAccountName);
        transferredAccount.setBalance(BigDecimal.ZERO);

        accountRepository.save(transferrerAccount);
        accountRepository.save(transferredAccount);
    }

    @Test
    public void should_transfer_money_from_one_account_to_another() throws SQLException {
        String transferrerAccountName = "transferrerAccount";
        String transferredAccountName = "transferredAccount";

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setTransferrerAccountName(transferrerAccountName);
        transferRequest.setTransferredAccountName(transferredAccountName);
        transferRequest.setTransferAmount(BigDecimal.TEN);

        TransferResponse transferResponse = transferService.transfer(transferRequest);

        assertNotNull(transferResponse);
        assertNotNull(transferResponse.getOperationResult());
        assertNotNull(transferResponse.getCreated());
        assertEquals(0, transferResponse.getOperationResult().getReturnCode().intValue());
        assertEquals(BigDecimal.TEN, transferResponse.getTransferAmount());
        assertEquals(transferrerAccountName, transferResponse.getTransferrerName());
        assertEquals(transferredAccountName, transferResponse.getTransferredName());
    }

    @After
    public void tearDown() {
        accountRepository.deleteAll();
    }
}