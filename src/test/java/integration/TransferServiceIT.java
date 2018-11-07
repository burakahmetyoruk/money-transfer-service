package integration;


import domain.entity.Account;
import domain.entity.Transfer;
import domain.repository.AccountRepository;
import domain.repository.TransferRepository;
import exception.NegativeBalanceException;
import model.transfer.TransferRequest;
import model.transfer.TransferResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.TransferService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransferServiceIT {

    private static final String TRANSFERRER_ACCOUNT_NAME = "transferrerAccountName";
    private static final String TRANSFERRED_ACCOUNT_NAME = "transferredAccountName";

    private TransferService transferService = TransferService.of();
    private AccountRepository accountRepository = AccountRepository.of();
    private TransferRepository transferRepository = TransferRepository.of();

    @Before
    public void setUp() {
        Account transferrerAccount = new Account();
        transferrerAccount.setName(TRANSFERRER_ACCOUNT_NAME);
        transferrerAccount.setBalance(BigDecimal.valueOf(30));
        accountRepository.save(transferrerAccount);

        Account transferredAccount = new Account();
        transferredAccount.setName(TRANSFERRED_ACCOUNT_NAME);
        transferredAccount.setBalance(BigDecimal.ZERO);
        accountRepository.save(transferredAccount);
    }

    @Test
    public void should_transfer_money_one_account_to_another() {
        Optional<Account> transferrerAccount = accountRepository.findByName(TRANSFERRER_ACCOUNT_NAME);
        Optional<Account> transferredAccount = accountRepository.findByName(TRANSFERRED_ACCOUNT_NAME);

        TransferRequest transferRequest = createTransferRequest(transferrerAccount.get(),
                transferredAccount.get(),
                BigDecimal.TEN);
        TransferResponse transferResponse = transferService.transfer(transferRequest);

        assertNotNull(transferResponse);
        assertNotNull(transferResponse.getOperationResult());
        assertEquals(0, transferResponse.getOperationResult().getReturnCode().intValue());

        Optional<Account> updatedTransferrerAccount = accountRepository.findByName(TRANSFERRER_ACCOUNT_NAME);
        Optional<Account> updatedTransferredAccount = accountRepository.findByName(TRANSFERRED_ACCOUNT_NAME);
        Optional<Transfer> transfer = transferRepository.findById(transferResponse.getTransferId());

        assertEquals(20, updatedTransferrerAccount.get().getBalance().intValue());
        assertEquals(10, updatedTransferredAccount.get().getBalance().intValue());
        assertEquals(transferrerAccount.get().getId(), transfer.get().getTransferrer().getId());
        assertEquals(transferredAccount.get().getId(), transfer.get().getTransferred().getId());
    }



    @Test(expected = NegativeBalanceException.class)
    public void should_not_transfer_money_when_transferrer_account_be_overdrawn() {
        Optional<Account> transferrerAccount = accountRepository.findByName(TRANSFERRER_ACCOUNT_NAME);
        Optional<Account> transferredAccount = accountRepository.findByName(TRANSFERRED_ACCOUNT_NAME);

        TransferRequest transferRequest = createTransferRequest(transferrerAccount.get(),
                transferredAccount.get(),
                BigDecimal.valueOf(35));
        transferService.transfer(transferRequest);
    }

    @After
    public void tearDown() {
        transferRepository.deleteAll();
        accountRepository.deleteAll();
    }

    private TransferRequest createTransferRequest(Account transferrer, Account transferred, BigDecimal amount) {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setTransferAmount(amount);
        transferRequest.setTransferrerAccountName(transferrer.getName());
        transferRequest.setTransferredAccountName(transferred.getName());
        return transferRequest;
    }
}