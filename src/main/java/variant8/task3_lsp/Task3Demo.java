package variant8.task3_lsp;

import java.util.List;

public final class Task3Demo {
    public static void run() {
        InMemoryDataRepository<String> repo = new InMemoryDataRepository<>();
        new DataSyncService<String>().syncEntities(repo, List.of("order-1", "order-2"));
        System.out.println("repo = " + repo.getAll());

        ReadOnlyAuditRepository<String> audit = new ReadOnlyAuditRepository<>(List.of("login", "logout"));
        System.out.println("audit = " + audit.getAll());
        // new DataSyncService<String>().syncEntities(audit, ...); // НЕ КОМПІЛЮЄТЬСЯ — і це правильно
    }
}
