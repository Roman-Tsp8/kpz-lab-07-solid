package variant8.task3_lsp;

import java.util.List;

/** Контракт читання: підтримують ВСІ репозиторії. */
public interface ReadableRepository<T> {
    /** @return незмінний знімок усіх сутностей. */
    List<T> getAll();
}
