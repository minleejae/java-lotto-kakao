package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_COUNT = 0;
    private static final int COUNT_INCREMENT = 1;
    private final List<LottoRank> lottoRanks;
    private final Cost cost;

    public LottoResult(List<LottoRank> lottoRanks, Cost cost) {
        this.lottoRanks = lottoRanks;
        this.cost = cost;
    }

    public Map<LottoRank, Integer> calculateStatistics() {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            statistics.put(lottoRank, statistics.getOrDefault(lottoRank, INITIAL_COUNT) + COUNT_INCREMENT);
        }
        return statistics;
    }

    public Double calculateProfit() {
        return lottoRanks.stream()
                .mapToDouble(LottoRank::getPrize)
                .sum() / cost.getSpent();
    }

    public List<LottoRank> getLottoRanks() {
        return lottoRanks;
    }
}
