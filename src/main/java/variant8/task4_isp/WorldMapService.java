package variant8.task4_isp;

import java.util.List;

/** Клієнт залежить лише від Movable — йому байдуже на магію чи торгівлю. */
public final class WorldMapService {
    public void moveAll(List<? extends Movable> units, int x, int y) {
        for (Movable unit : units) {
            unit.move(x, y);
        }
    }
}
