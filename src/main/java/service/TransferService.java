package service;

import domain.entity.Account;
import domain.entity.Transfer;
import domain.repository.TransferRepository;
import domain.repository.TransferRepositoryImpl;
import exception.AccountsAreSameException;
import exception.NegativeBalanceException;
import model.transfer.TransferRequest;
import model.transfer.TransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Random;

public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final TransferService transferService = new TransferService();

    private final AccountService accountService;
    private final TransferRepository<Transfer> transferRepository;

    public static TransferService of() {
        return transferService;
    }

    public TransferResponse transfer(TransferRequest transferRequest) throws SQLException {
        //Retrieve Transferrer Account
        Account transferrerAccount = accountService.retrieveAccount(transferRequest.getTransferrerAccountName());
        //Retrieve TransferredAccount
        Account transferredAccount = accountService.retrieveAccount(transferRequest.getTransferredAccountName());

        //Check whether accounts are same
        if (transferrerAccount.equals(transferredAccount)) {
            throw new AccountsAreSameException();
        }
        //Transfer Money
        TransferResponse transferResponse = saveTransfer(transferrerAccount,
                transferredAccount,
                transferRequest.getTransferAmount());
        logger.info("Transfer Successful From: {} To: {}", transferrerAccount.getName(), transferredAccount.getName());
        return transferResponse;
    }


    TransferResponse saveTransfer(Account transferrerAccount, Account transferredAccount, final BigDecimal transferAmount) throws SQLException {
        //Account must not be overdrawn
        if (transferrerAccount.getBalance().compareTo(transferAmount) < 0) {
            logger.info("{} Balance must be bigger than Transfer Amount", transferrerAccount.getName());
            throw new NegativeBalanceException(transferrerAccount.getName());
        }
        logger.info("Transfer Amount:{}, From: {}, To: {}",
                transferAmount,
                transferrerAccount.getName(),
                transferredAccount.getName());

        transferrerAccount.setBalance(transferrerAccount.getBalance().subtract(transferAmount));
        accountService.update(transferrerAccount);

        transferredAccount.setBalance(transferredAccount.getBalance().add(transferAmount));
        accountService.update(transferredAccount);

        Transfer transfer = new Transfer();
        transfer.setId(createId());
        transfer.setTransferAmount(transferAmount);
        transfer.setTransferrer(transferrerAccount);
        transfer.setTransferred(transferredAccount);
        transferRepository.save(transfer);

        return new TransferResponse(transfer.getId(),
                System.nanoTime(),
                transferAmount,
                transferrerAccount.getName(),
                transferredAccount.getName());
    }

    private TransferService() {
        this.accountService = AccountService.of();
        this.transferRepository = TransferRepositoryImpl.of();
    }

    private long createId() {
        return new Random().nextLong();
    }
}