package variant8.task4_isp;

/** Нова роль - коваль  */
public final class BlacksmithCharacter implements Movable, Crafter, Trader {
    @Override public void move(int x, int y) { System.out.println("Blacksmith moved to (" + x + ", " + y + ")."); }
    @Override public void craftItem(String recipe) { System.out.println("Blacksmith crafted " + recipe + "."); }
    @Override public void tradeWithNpc(int npcId) { System.out.println("Blacksmith traded with NPC #" + npcId + "."); }
}
