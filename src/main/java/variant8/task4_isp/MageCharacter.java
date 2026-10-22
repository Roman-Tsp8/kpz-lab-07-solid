package variant8.task4_isp;

public final class MageCharacter implements Movable, SpellCaster, Healer {
    @Override public void move(int x, int y) { System.out.println("Mage moved to (" + x + ", " + y + ")."); }
    @Override public void castSpell(String spellName, int manaCost) { System.out.println("Mage cast spell " + spellName + "."); }
    @Override public void castHeal(int hpAmount) { System.out.println("Mage restored " + hpAmount + " HP."); }
}
