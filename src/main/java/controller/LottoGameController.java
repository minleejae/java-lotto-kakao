package controller;

import generator.RandomNumberGenerator;
import model.LottoGame;
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
        LottoGame lottoGame = new LottoGame(lottoGameView.requestCost(), new RandomNumberGenerator());

        lottoGameView.displayLottoAmount(lottoGame.calculateLottoAmount());

        Lottos lottos = lottoGame.getLottos();
        lottoGameView.displayLottos(lottos);

        List<Integer> winningNumbers = lottoGameView.requestWinningNumbers();
        int bonusNumber = lottoGameView.requestBonusNumber();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult lottoResult = lottoGame.calculateResult(winningLotto);

        lottoGameView.displayStatistics(lottoResult.calculateStatistics());
        lottoGameView.displayProfit(lottoResult.calculateProfit());
    }
}
