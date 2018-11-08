package domain;

import domain.entity.Account;
import domain.entity.Transfer;
import org.apache.commons.dbutils.ResultSetHandler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferResultSetHandler implements ResultSetHandler<Transfer> {
    @Override
    public Transfer handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Long transferrerId = resultSet.getLong("transferrer_id");
            Long transferredId = resultSet.getLong("transferred_id");
            BigDecimal transferAmount = resultSet.getBigDecimal("transfer_amount");

            Account transferrer = retrieveAccount(transferrerId);
            Account transferred = retrieveAccount(transferredId);
            return new Transfer(id, transferAmount, transferrer, transferred);
        }
        return null;
    }

    private Account retrieveAccount(Long accountId) {
        Account account = new Account();
        account.setId(accountId);
        return account;
    }
}