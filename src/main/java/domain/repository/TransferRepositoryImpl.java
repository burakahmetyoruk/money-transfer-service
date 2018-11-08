package domain.repository;

import domain.H2DataBaseService;
import domain.TransferResultSetHandler;
import domain.entity.Transfer;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class TransferRepositoryImpl implements TransferRepository<Transfer> {

    private static final Logger logger = LoggerFactory.getLogger(TransferRepositoryImpl.class);
    private static TransferRepository<Transfer> transferRepository = new TransferRepositoryImpl();

    public static TransferRepository<Transfer> of() {
        return transferRepository;
    }

    @Override
    public void save(Transfer transfer) throws SQLException {
        String sqlQuery = "INSERT INTO TRANSFER (ID, TRANSFER_AMOUNT, TRANSFERRER_ID, TRANSFERRED_ID, CREATED) VALUES (?,?,?,?,?)";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, sqlQuery, transfer.getId(),
                    transfer.getTransferAmount(),
                    transfer.getTransferrer().getId(),
                    transfer.getTransferred().getId(),
                    new Date(System.nanoTime()));
        }
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        String query = "SELECT * FROM TRANSFER WHERE id = ?";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            final Transfer transfer = queryRunner.query(connection, query, new TransferResultSetHandler(), id);
            return Optional.ofNullable(transfer);
        } catch (SQLException e) {
            logger.error("Error occurred when find Transfer from DB", e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        String sqlQuery = "DELETE FROM TRANSFER";
        try (Connection connection = H2DataBaseService.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, sqlQuery);
        } catch (SQLException e) {
            logger.error("Error occurred when delete all transfers from DB", e);
        }
    }
}