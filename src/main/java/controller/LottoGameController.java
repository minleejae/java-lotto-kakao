package controller;

import model.*;
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

        WinningLotto winningLotto = requestWinningLotto();
        LottoResult lottoResult = lottos.calculateResult(winningLotto);

        displayResult(lottoResult, cost);
    }

    private Cost requestCost() {
        return new Cost(lottoGameView.requestCost());
    }

    private Lottos generateLottos(Cost cost) {
        int manualLottoAmount = lottoGameView.requestManualLottoAmount();
        List<List<Integer>> manualLottoNumbers = lottoGameView.requestManualLottoNumbers(manualLottoAmount);
        Lottos manualLottos = LottoGenerator.generateManualLottos(manualLottoNumbers);

        int autoLottoAmount = cost.calculateAutoLottoAmount(manualLottoAmount);
        Lottos autoLottos = LottoGenerator.generateAutoLottos(autoLottoAmount);
        Lottos lottos = manualLottos.merge(autoLottos);

        lottoGameView.displayLottoAmount(manualLottoAmount, autoLottoAmount);
        lottoGameView.displayLottos(lottos);
        return lottos;
    }

    private WinningLotto requestWinningLotto() {
        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private void displayResult(LottoResult lottoResult, Cost cost) {
        lottoGameView.displayStatistics(lottoResult.calculateStatistics());
        lottoGameView.displayProfit(lottoResult.calculateProfit(cost));
    }
}
