import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoGameTest {
    private static final int ONE = 1;
    private TestLottoGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestLottoGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void calculateResult() {
        Lottos lottos = new Lottos(ONE, testNumberGenerator);
        LottoGame lottoGame = new LottoGame(lottos);

        Lotto inputLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        LottoResult lottoResult = lottoGame.calculateResult(winningLotto);

        assertEquals(LottoRank.FIRST, lottoResult.getLottoRanks().get(0));
    }
}
