package model;

import generator.NumberGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private final Cost cost;
    private final Lottos lottos;

    public LottoGame(int cost, NumberGenerator numberGenerator) {
        this.cost = new Cost(cost);
        int amounts = calculateLottoAmount();
        lottos = new Lottos(amounts, numberGenerator);
    }

    public int calculateLottoAmount() {
        return cost.calculateLottoAmount();
    }

    public Map<LottoRank, Integer> calculateStatistics(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> statistics = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            statistics.put(lottoRank, statistics.getOrDefault(lottoRank, 0) + 1);
        }
        return statistics;
    }

    public List<LottoRank> calculateResult(WinningLotto winningLotto) {
        return this.lottos.calculateResult(winningLotto);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public Double calculateProfit(List<LottoRank> lottoRanks) {
        return lottoRanks.stream()
                .mapToDouble(LottoRank::getPrize)
                .sum() / cost.getSpent();
    }
}
