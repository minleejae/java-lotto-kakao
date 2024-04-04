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
        int manualLottoAmount = lottoGameView.requestManualLottoAmount();
        List<List<Integer>> manualLottoNumbers = lottoGameView.requestManualLottoNumbers(manualLottoAmount);

        Lottos lottos = generateLottos(cost, manualLottoAmount, manualLottoNumbers);
        displayLottos(lottos, cost, manualLottoAmount);

        WinningLotto winningLotto = requestWinningLotto();
        LottoResult lottoResult = calculateResult(lottos, winningLotto);

        displayResult(lottoResult, cost);
    }

    private Cost requestCost() {
        return new Cost(lottoGameView.requestCost());
    }

    private Lottos generateLottos(Cost cost, int manualLottoAmount, List<List<Integer>> manualLottoNumbers) {
        int autoLottoAmount = cost.calculateAutoLottoAmount(manualLottoAmount);
        RandomLottoGenerator numberGenerator = new RandomLottoGenerator();
        return Lottos.of(manualLottoNumbers, autoLottoAmount, numberGenerator);
    }

    private void displayLottos(Lottos lottos, Cost cost, int manualLottoAmount) {
        int autoLottoAmount = cost.calculateAutoLottoAmount(manualLottoAmount);
        lottoGameView.displayLottoAmount(manualLottoAmount, autoLottoAmount);
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
