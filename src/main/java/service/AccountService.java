package service;


import domain.entity.Account;
import domain.repository.AccountRepository;
import domain.repository.AccountRepositoryImpl;
import exception.AccountAlreadyExistException;
import exception.AccountNotFoundException;
import model.account.AccountRequest;
import model.account.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final AccountService accountService = new AccountService();

    private final AccountRepository<Account> accountRepository;

    public static AccountService of() {
        return accountService;
    }

    public AccountResponse create(AccountRequest accountRequest) throws SQLException {
        BigDecimal initialBalance = BigDecimal.valueOf(1000L);
        Optional<Account> existingAccount = accountRepository.findByName(accountRequest.getAccountName());
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            logger.info("Account already exist With Name: {}", account.getName());
            throw new AccountAlreadyExistException(account.getName());
        }
        Account account = new Account();
        account.setName(accountRequest.getAccountName());
        account.setBalance(initialBalance);
        accountRepository.save(account);
        logger.info("Account Created With Name: {}", account.getName());
        return new AccountResponse(account.getId(), account.getName(), initialBalance);
    }

    public AccountResponse retrieve(String accountName) {
        Account account = retrieveAccount(accountName);
        return new AccountResponse(account.getId(), account.getName(), account.getBalance());
    }

    void save(Account account) throws SQLException {
        accountRepository.save(account);
    }

    void update(Account account) throws SQLException {
        accountRepository.update(account);
    }

    Account retrieveAccount(String accountName) {
        Optional<Account> account = accountRepository.findByName(accountName);
        return account.orElseThrow(() -> new AccountNotFoundException(accountName));
    }

    private AccountService() {
        this.accountRepository = AccountRepositoryImpl.of();
    }
}