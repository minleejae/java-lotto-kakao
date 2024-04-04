package controller;

import generator.RandomLottoGenerator;
import model.Cost;
import model.LottoResult;
import model.Lottos;
import model.WinningLotto;
import view.LottoGameView;

import java.util.List;

public class LottoGameController {

    private final LottoGameView lottoGameView;

    public LottoGameController(LottoGameView lottoGameView) {
        this.lottoGameView = lottoGameView;
    }

    public void playGame() {
        Cost cost = requestCost();
        Lottos lottos = generateLottos(cost);
        displayLottos(lottos, cost);

        WinningLotto winningLotto = requestWinningLotto();
        LottoResult lottoResult = calculateResult(lottos, winningLotto);

        displayResult(lottoResult, cost);
    }

    private Cost requestCost() {
        return new Cost(lottoGameView.requestCost());
    }

    private Lottos generateLottos(Cost cost) {
        RandomLottoGenerator numberGenerator = new RandomLottoGenerator();
        int amounts = cost.calculateLottoAmount();
        return Lottos.of(amounts, numberGenerator);
    }

    private void displayLottos(Lottos lottos, Cost cost) {
        int amounts = cost.calculateLottoAmount();
        lottoGameView.displayLottoAmount(amounts);
        lottoGameView.displayLottos(lottos);
    }

    private WinningLotto requestWinningLotto() {
        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private LottoResult calculateResult(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateResult(winningLotto);
    }

    private void displayResult(LottoResult lottoResult, Cost cost) {
        lottoGameView.displayStatistics(lottoResult.calculateStatistics());
        lottoGameView.displayProfit(lottoResult.calculateProfit(cost));
    }
}
