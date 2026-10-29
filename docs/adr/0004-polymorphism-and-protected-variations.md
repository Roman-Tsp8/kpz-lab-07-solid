# ADR-0004: Polymorphism + Protected Variations (тарифи та страхування)

- **Статус:** Прийнято

## QOC
**Question:** Як прибрати `if/else if` за типом доставки й захистити ядро від змін тарифів/страховки?

| Option | Open/Closed | Читабельність | Тестованість | Компроміс |
|---|---|---|---|---|
| O1. `switch` у `Shipment` | −− | + | − | Кожен новий тип правитиме ядро |
| O2. `enum DeliveryType` із полем-множником | + | ++ | + | Не підходить, коли правило стане алгоритмом (не лише множник) |
| **O3. Інтерфейс `TransportTariff` + `Standard/Express/FreightTariff` + реєстр у `TariffPricingCatalog` (Dictionary Dispatch)** | ++ | + | ++ | Більше класів |

**Вибір:** O3 (O2 прийнятний як спрощення, якщо правила залишаться «множниками»).

## Рішення
- **Polymorphism:** `WeightBasedTariff` (вага × 15 × множник) → 3 нащадки (1.0 / 1.8 / 1.2).
- **Protected Variations:** точки нестабільності за інтерфейсами — `TransportTariff` (алгоритм тарифу), `InsurancePolicy` (правило страхування, 0.5%), `PricingCatalog` (вибір правила), `ShipmentRepository` (сховище), `DispatchListener` (email/аудит).
- Невідомий тип → `DomainException` з переліком доступних (у legacy мовчки діяв як Standard).
- Новий тип: 1 клас + 1 рядок у `Main`. Існуючий код не змінюється.

## Наслідки
(+) Нова політика не торкається ядра; (−) реєстрація тарифів — у Composition Root (не авто-виявлення).
