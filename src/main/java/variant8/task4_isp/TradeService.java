package variant8.task4_isp;

/** Клієнт торгівлі залежить лише від Trader. */
public final class TradeService {
    public void openTrade(Trader trader, int npcId) {
        trader.tradeWithNpc(npcId);
    }
}
