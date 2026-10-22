package variant8.task3_lsp;

import java.util.List;
import java.util.Objects;

public final class DataSyncService<T> {
    /** Приймає лише WritableRepository: передати аудит-журнал — помилка компіляції, а не збій у рантаймі. */
    public void syncEntities(WritableRepository<T> targetRepository, List<T> incomingData) {
        Objects.requireNonNull(targetRepository, "targetRepository");
        Objects.requireNonNull(incomingData, "incomingData");
        for (T item : incomingData) {
            targetRepository.add(item);
        }
    }
}
