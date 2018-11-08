package domain.repository;

import domain.entity.Transfer;

import java.sql.SQLException;
import java.util.Optional;

public interface TransferRepository<T> {

    void save(T t) throws SQLException;

    Optional<Transfer> findById(Long id);

    void deleteAll();
}