package variant8.task3_lsp;

import java.util.List;
import java.util.Objects;

/**
 * Журнал аудиту — тільки читання. НЕ є підтипом WritableRepository, тому
 * методів add/update/remove, що кидають винятки, більше не існує.
 * Інваріант «записи незмінні» гарантується типом та захисною копією.
 */
public final class ReadOnlyAuditRepository<T> implements ReadableRepository<T> {
    private final List<T> records;

    public ReadOnlyAuditRepository(List<T> records) {
        this.records = List.copyOf(Objects.requireNonNull(records, "records"));
    }

    @Override
    public List<T> getAll() {
        return records;
    }
}
