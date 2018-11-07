package domain.repository;

import domain.entity.Transfer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TransferRepository {

    private static final TransferRepository transferRepository = new TransferRepository();
    private static AtomicLong idCounter = new AtomicLong(1L);
    private final Map<Long, Transfer> transferMap = new ConcurrentHashMap<>();

    public static TransferRepository of() {
        return transferRepository;
    }

    public void save(Transfer transfer) {
        transfer.setId(idCounter.incrementAndGet());
        transferMap.put(transfer.getId(), transfer);
    }

    public Optional<Transfer> findById(Long id) {
        return Optional.ofNullable(transferMap.get(id));
    }

    public void deleteAll() {
        transferMap.clear();
    }
}