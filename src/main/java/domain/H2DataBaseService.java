package domain;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DataBaseService {

    private static Logger logger = LoggerFactory.getLogger(H2DataBaseService.class);

    public static void init() {

        try (Connection connection = DriverManager.getConnection ("jdbc:h2:mem:test_mem;DB_CLOSE_DELAY=-1;", "","");) {
            QueryRunner queryRunner = new QueryRunner();
            
            String accountCreateQuery = "CREATE TABLE ACCOUNT (" +
                    "  ID bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "  BALANCE decimal(19,2) NOT NULL," +
                    "  NAME varchar(255) NOT NULL," +
                    "  UNIQUE KEY unq_account_name (NAME)" +
                    ");";
            String transferCreateQuery = "CREATE TABLE TRANSFER (" +
                    "  ID bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "  TRANSFER_AMOUNT decimal(20,2) DEFAULT NULL," +
                    "  TRANSFERRER_ID bigint(20) DEFAULT NULL," +
                    "  TRANSFERRED_ID bigint(20) DEFAULT NULL," +
                    "  created TIMESTAMP NOT NULL," +
                    "  FOREIGN KEY (TRANSFERRER_ID) REFERENCES account (id)," +
                    "  FOREIGN KEY (TRANSFERRED_ID) REFERENCES account (id)" +
                    ");";

            queryRunner.update(connection, accountCreateQuery + transferCreateQuery);
            logger.info("DB Schema created successfully!");
        } catch (Exception e) {
            logger.error("Failed to init DB Schema!", e);
        }
    }

    public static void drop() {
        try (Connection connection = DriverManager.getConnection ("jdbc:h2:mem:test_mem;DB_CLOSE_DELAY=-1;", "","");) {
            QueryRunner queryRunner = new QueryRunner();

            String accountDropQuery = "DROP TABLE ACCOUNT;";
            String transferDropQuery = "DROP TABLE TRANSFER;";

            queryRunner.update(connection, accountDropQuery + transferDropQuery);
            logger.info("DB Schema dropped successfully!");
        } catch (Exception e) {
            logger.error("Failed to drop DB Schema!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection ("jdbc:h2:mem:test_mem;IFEXISTS=TRUE;", "","");
    }
}