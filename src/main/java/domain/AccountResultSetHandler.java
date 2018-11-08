package domain;

import domain.entity.Account;
import org.apache.commons.dbutils.ResultSetHandler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountResultSetHandler implements ResultSetHandler<Account> {
    @Override
    public Account handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String accountName = resultSet.getString("name");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            return new Account(id, accountName, balance);
        }
        return null;
    }
}