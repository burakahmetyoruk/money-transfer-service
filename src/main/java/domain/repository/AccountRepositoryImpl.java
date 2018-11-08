package domain.repository;

import domain.AccountResultSetHandler;
import domain.H2DataBaseService;
import domain.entity.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository<Account> {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);
    private static final AccountRepository<Account> accountRepository = new AccountRepositoryImpl();

    public static AccountRepository<Account> of() {
        return accountRepository;
    }

    @Override
    public void save(Account account) throws SQLException {
        String sqlQuery = "INSERT INTO ACCOUNT (NAME, BALANCE) VALUES (?,?)";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, sqlQuery, account.getName(), account.getBalance());
        }
    }

    @Override
    public void update(Account account) throws SQLException {
        String sqlQuery = "UPDATE ACCOUNT SET BALANCE = ? WHERE NAME = ?";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, sqlQuery, account.getBalance(), account.getName());
        }
    }

    @Override
    public Optional<Account> findByName(String name) {
        String query = "SELECT * FROM ACCOUNT WHERE name = ?";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            final Account account = queryRunner.query(connection, query, new AccountResultSetHandler(), name);
            return Optional.ofNullable(account);
        } catch (SQLException e) {
            logger.error("Error occurred when find Account from DB", e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM ACCOUNT";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, query);
        } catch (SQLException e) {
            logger.error("Error occurred when delete all Accounts from DB", e);
        }
    }
}