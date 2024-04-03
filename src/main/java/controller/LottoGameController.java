package controller;

import generator.RandomNumberGenerator;
import model.*;
import view.LottoGameView;

import java.util.List;

public class LottoGameController {

    private final LottoGameView lottoGameView;

    public LottoGameController(LottoGameView lottoGameView) {
        this.lottoGameView = lottoGameView;
    }

    public void playGame() {
        Cost cost = new Cost(lottoGameView.requestCost());
        RandomNumberGenerator numberGenerator = new RandomNumberGenerator();

        int amounts = cost.calculateLottoAmount();
        Lottos lottos = new Lottos(amounts, numberGenerator);

        lottoGameView.displayLottoAmount(amounts);
        lottoGameView.displayLottos(lottos);

        LottoGame lottoGame = new LottoGame(lottos);

        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult lottoResult = lottoGame.calculateResult(winningLotto);

        lottoGameView.displayStatistics(lottoResult.calculateStatistics());
        lottoGameView.displayProfit(lottoResult.calculateProfit(cost));
    }
}
