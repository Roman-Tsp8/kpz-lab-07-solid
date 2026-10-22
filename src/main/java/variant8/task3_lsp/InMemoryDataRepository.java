package variant8.task3_lsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Повноцінний CRUD-репозиторій. */
public class InMemoryDataRepository<T> implements WritableRepository<T> {
    protected final List<T> items = new ArrayList<>();

    @Override
    public void add(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        items.add(entity);
        System.out.println("[Repository] Entity added.");
    }

    @Override
    public void update(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        int index = items.indexOf(entity);
        if (index < 0) {
            throw new IllegalStateException("Cannot update: entity not found in repository.");
        }
        items.set(index, entity);
        System.out.println("[Repository] Entity updated.");
    }

    @Override
    public void remove(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        items.remove(entity);
        System.out.println("[Repository] Entity removed.");
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }
}
