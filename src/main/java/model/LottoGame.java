package model;

import generator.NumberGenerator;

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

    public LottoResult calculateResult(WinningLotto winningLotto) {
        return new LottoResult(this.lottos.calculateResult(winningLotto), cost);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
