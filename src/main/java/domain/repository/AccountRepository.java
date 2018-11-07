package domain.repository;

import domain.entity.Account;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class AccountRepository {

    private static final AccountRepository accountRepository = new AccountRepository();
    private static AtomicLong idCounter = new AtomicLong(1L);
    private final Map<Long, Account> accountMap = new ConcurrentHashMap<>();

    public static AccountRepository of() {
        return accountRepository;
    }

    public Optional<Account> findByName(String name) {

        for (Account account : accountMap.values()) {
            if (account.getName().equals(name)) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    public void save(Account account) {
        account.setId(idCounter.incrementAndGet());
        accountMap.put(account.getId(), account);
    }

    public void deleteAll() {
        accountMap.clear();
    }
}