# Метричний аналіз (Завдання 5) — Варіант 8

Підрахунок CBO: `python3 tools/cbo.py <тека>` (наближення за регулярками: згадування іншого класу проєкту у файлі —
поля, параметри, виклики, extends/implements, new). Примітиви та `java.*` (у т.ч. `Scanner`, `List`) не враховуються за методичкою.
LCOM4 пораховано вручну (аксесори, конструктори й статичні фабрики виключено, як дозволяє методичка).
**Якщо викладач використовує інший інструмент (CK, SonarQube, IntelliJ MetricsReloaded) — числа можуть відрізнятися на ±1; перерахуйте й оновіть таблицю.**

## 1. CBO (ціль ≤ 4 для сутностей і контролерів)

| Клас | До (legacy) | Після | Склад зв'язків після |
|---|---|---|---|
| `LegacyLogisticsProcessor` | **3** | — (розділено) | `Sender`, `Cargo`, `Shipment` |
| `Shipment` (legacy, анемічна) | 2 | — | `Sender`, `Cargo` |
| `Shipment` (Aggregate Root) | — | **4** | `Cargo`, `Quote`, `Sender`, `ShipmentSnapshot` |
| `ShipmentController` | — | **4** | `CreateShipmentCommand`, `ShipmentFactory`, `DispatchService`, `ShipmentSnapshot` |
| `DispatchService` | — | 4 | `Shipment`, `ShipmentSnapshot`, `ShipmentRepository`, `DispatchListener` |
| `ShipmentFactory` | — | 4 | `CreateShipmentCommand`, `PricingCatalog`, `Shipment`, `TrackingIdGenerator` |
| `ConsoleApp` | — | 4 | `ConsoleShipmentView`, `ConsoleCommandParser`, `WaybillFormatter`, `ShipmentController` |
| `TariffPricingCatalog` | — | **6** ⚠ | `Cargo`, `Quote`, `TransportTariff`, `InsurancePolicy`, `PricingCatalog`, `DomainException` |
| `Main` (Composition Root) | — | 17 | усі конкретні класи (так і має бути — виняток) |

### Чесні висновки (те, що викладач може запитати)
1. **CBO legacy-процесора вже було 3 (≤ 4).** Бо весь код — в одному класі, і зовнішніх класів майже немає. Метрика CBO сама по собі
   *не показує* проблему God Method: це приклад, коли «низьке зачеплення» = «усе склеєно в одне місце». Тому «зниження CBO» тут довести **не можна**
   і вигадувати його не варто. Коректне формулювання: *CBO усіх ключових класів лишається ≤ 4, а відповідальності розділені.*
2. Якщо рахувати `Scanner` + `System.out` як інфраструктуру (а не «базові типи платформи»), legacy-CBO = 4–5 → і тоді нова верхня межа 4 — це покращення на рівні шуму.
3. `TariffPricingCatalog` = 6 > 4. Це доменний сервіс, а не «сутність/контролер», тож формально поза нормативом, але слабке місце.
   Варіант виправлення: прибрати власний `DomainException` (кидати `IllegalArgumentException`) і не реалізовувати `PricingCatalog` окремим інтерфейсом — отримаємо 4; я залишив 6, бо інтерфейс і доменна помилка корисніші за красиву цифру.
4. Метрика-обманка: якщо «покращувати» CBO, злиплюючи класи — вона впаде. Тому її треба читати разом з LCOM4 і розподілом відповідальностей.

## 2. LCOM4 (ціль = 1)

| Клас | До | Після | Обґрунтування (компоненти графа методів) |
|---|---|---|---|
| `LegacyLogisticsProcessor` | 1 (формально) | — | один метод → один компонент. **Метрика сліпа**: у методі 7 відповідальностей |
| `Sender`/`Cargo`/`Shipment` (legacy) | не визначено | — | методів немає (лише публічні поля) |
| `Shipment` | — | **1** | `snapshot` ↔ `totalCost` (виклик), `snapshot` ↔ `dispatch` (спільне поле `dispatched`) |
| `ShipmentController` | — | **1** | `registerShipment` і `listShipments` ділять поле `dispatcher` |
| `DispatchService` | — | **1** | `dispatch` і `registry` ділять `repository` |
| `TariffPricingCatalog` | — | **1** | `quote` → `availableTypes` (виклик) |
| `ConsoleApp` | — | **1** | `run` викликає `createShipment`/`showRegistry`; обидва ділять `view`, `controller` |
| `WaybillFormatter` | — | **1** | `waybill` і `registryLine` викликають `money` |
| `ConsoleCommandParser` | — | **1** | `parse` викликає `nz` і `number` |

## 3. Додаткові метрики, які реально показують покращення

| Показник | До | Після |
|---|---|---|
| Публічних змінних полів у сутностях | **15** | **0** |
| Відповідальностей в одному методі/класі | **7** (ввід, створення, тариф, страховка, форматування, реєстр, email) | **1** на клас |
| Розгалужень за типом (`if/else if`) | 2 + мовчазний default | **0** (поліморфізм + словник) |
| Валідація вхідних даних | немає | в конструкторах `Sender`/`Cargo`/`Quote`/`Shipment` + парсер |
| Автотестів | 0 | 12 (`LogisticsTests`) |
| Зміна для нового типу доставки | правка `if` у процесорі | новий клас + 1 рядок у `Main` |

## 4. Нестабільність шарів (I = Ce / (Ca + Ce), рівень класів між пакетами)

| Шар | Ce | Ca | I |
|---|---|---|---|
| `domain` | 0 | 11 | **0.00** (стабільне ядро) |
| `application` | 5 | 7 | 0.42 |
| `infrastructure` | 5 | 0 | **1.00** |
| `ui` | 3 | 0 | **1.00** |

Залежності йдуть `ui → application → domain` та `infrastructure → application(порти)/domain` — від нестабільного до стабільного (Stable Dependencies виконано).
Домен не залежить ні від чого поза собою. `Main` не враховано (Composition Root).
