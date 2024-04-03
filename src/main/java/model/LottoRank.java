package model;

public enum LottoRank {
    FIRST(1, 6, false, 2000000000),
    SECOND(2, 5, true, 30000000),
    THIRD(3, 5, false, 1500000),
    FOURTH(4, 4, false, 50000),
    FIFTH(5, 3, false, 5000),
    FAIL(6, 0, false, 0);

    private final int rank;
    private final int matchCount;
    private final boolean matchBonus;
    private final int money;

    LottoRank(int rank, int matchCount, boolean matchBonus, int money) {
        this.rank = rank;
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.money = money;
    }

    public static LottoRank of(int matchCount, boolean matchBonus) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.matchCount == matchCount && (!rank.matchBonus || matchBonus)) {
                return rank;
            }
        }
        return matchCount > 2 ? of(6 - matchCount + 2, false) : FAIL;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return money;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }
}
