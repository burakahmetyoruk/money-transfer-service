package domain.repository;

import java.sql.SQLException;
import java.util.Optional;

public interface AccountRepository<T> {

    Optional<T> findByName(String name);
    void save(T t) throws SQLException;
    void update(T t) throws SQLException;
    void deleteAll();
}