import controller.LottoGameController;
import view.LottoGameView;

public class LottoGameApplication {

    public static void main(String[] args) {

        LottoGameView lottoGameView = new LottoGameView();
        LottoGameController lottoGameController = new LottoGameController(lottoGameView);

        lottoGameController.playGame();
    }
}
