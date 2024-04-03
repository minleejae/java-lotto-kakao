package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final List<LottoRank> lottoRanks;
    private final Cost cost;

    public LottoResult(List<LottoRank> lottoRanks, Cost cost) {
        this.lottoRanks = lottoRanks;
        this.cost = cost;
    }

    public Map<LottoRank, Integer> calculateStatistics() {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            statistics.put(lottoRank, statistics.getOrDefault(lottoRank, 0) + 1);
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
