package variant8.task4_isp;

import java.util.List;

public final class Task4Demo {
    public static void run() {
        MageCharacter mage = new MageCharacter();
        NpcVendor vendor = new NpcVendor();
        BlacksmithCharacter smith = new BlacksmithCharacter();

        new WorldMapService().moveAll(List.of(mage, vendor, smith), 10, 20);
        new BattleService().castFireball(mage);
        new BattleService().supportAlly(mage, 50);
        new TradeService().openTrade(vendor, 7);
        new TradeService().openTrade(smith, 8);
        smith.craftItem("iron sword");
        // new BattleService().castFireball(vendor); // НЕ КОМПІЛЮЄТЬСЯ — торговець не SpellCaster
    }
}
