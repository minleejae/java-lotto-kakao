import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosTest {
    private static final int NO_AUTO_LOTTOS = 0;
    private static final int ONE_AUTO_LOTTO = 1;

    private TestLottoGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestLottoGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void calculateResultWithManualLottoNumbers() {
        Lottos lottos = Lottos.of(List.of(List.of(1, 2, 3, 4, 5, 6)), NO_AUTO_LOTTOS, testNumberGenerator);

        Lotto inputLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        LottoResult lottoResult = lottos.calculateResult(winningLotto);

        assertEquals(LottoRank.FIRST, lottoResult.getLottoRanks().get(0));
    }

    @Test
    void calculateResultAutoLottoNumbers() {
        Lottos lottos = Lottos.of(List.of(), ONE_AUTO_LOTTO, testNumberGenerator);

        Lotto inputLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        LottoResult lottoResult = lottos.calculateResult(winningLotto);

        assertEquals(LottoRank.FIRST, lottoResult.getLottoRanks().get(0));
    }
}
