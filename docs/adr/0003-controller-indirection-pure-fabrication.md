# ADR-0003: Controller + Indirection + Pure Fabrication

- **Статус:** Прийнято

## QOC
**Question:** Хто координує use-case «оформити відправку» і як відокремити його від консолі та сховища?

| Option | Thin UI | Low Coupling (CBO≤4) | Заміна сховища/email | Складність |
|---|---|---|---|---|
| O1. Усе в `ConsoleApp` | −− | − | −− | ++ |
| O2. Один `ShipmentService` з усім (assemble + save + email + audit) | + | −− (CBO ≈ 8) | − | + |
| **O3. `ShipmentController` → `ShipmentFactory` + `DispatchService`; порти `ShipmentRepository`, `DispatchListener`, `TrackingIdGenerator`** | ++ | ++ | ++ | − |

**Вибір:** O3.

## Рішення
- **Controller:** `ShipmentController` — приймає `CreateShipmentCommand`, повертає `ShipmentSnapshot`; без `System.in/out`.
- **Indirection:** інтерфейси `ShipmentRepository`, `DispatchListener`, `TrackingIdGenerator`, `PricingCatalog` між контролером і деталями.
- **Pure Fabrication:** `ShipmentFactory`, `DispatchService`, `ConsoleCommandParser`, `WaybillFormatter`, `CompositeDispatchListener`, `RegistryLogListener`, `EmailDispatchListener`, `InMemoryShipmentRepository` — класів із бізнес-домену немає, вони існують заради High Cohesion сутностей.
- Форматування накладної винесено з домену у `WaybillFormatter` (презентація ≠ бізнес).
- `Main` — Composition Root; єдине місце, що знає конкретні класи.

## Наслідки
(+) CLI можна замінити на REST без змін контролера; (+) email/реєстр — підміняються;
(−) порядок виводу змінився: рядки `[Реєстр]`/`[Email]` друкуються під час відправки, **до** накладної (у legacy — після). Функціонально еквівалентно.
