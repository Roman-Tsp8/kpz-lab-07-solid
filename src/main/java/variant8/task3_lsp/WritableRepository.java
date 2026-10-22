package variant8.task3_lsp;

/** Контракт запису: лише для репозиторіїв, які справді дозволяють зміни. */
public interface WritableRepository<T> extends ReadableRepository<T> {
    void add(T entity);

    /** @throws IllegalStateException якщо такої сутності ще немає (fail-fast). */
    void update(T entity);

    void remove(T entity);
}
