package variant8.task4_isp;

/** Клієнт бою залежить лише від ролей SpellCaster та Healer. */
public final class BattleService {
    public void castFireball(SpellCaster caster) {
        caster.castSpell("Fireball", 30);
    }

    public void supportAlly(Healer healer, int hp) {
        healer.castHeal(hp);
    }
}
