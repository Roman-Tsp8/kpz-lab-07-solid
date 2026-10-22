package variant8.task4_isp;

public final class NpcVendor implements Movable, Trader {
    @Override public void move(int x, int y) { System.out.println("Vendor walked to shop stall at (" + x + ", " + y + ")."); }
    @Override public void tradeWithNpc(int npcId) { System.out.println("Vendor opened shop inventory for player."); }
}
