import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class LottoTest {

    TestNumberGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestNumberGenerator(List.of(
                List.of(1, 2, 3, 4, 5, 6)
        ));
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 300})
    void validateLottoInvalidCost(int cost) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LottoGame(cost, testNumberGenerator));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 2000})
    void validateLottoCost(int cost) {
        Assertions.assertDoesNotThrow(() -> new LottoGame(cost, testNumberGenerator));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 1600})
    void calculateLottoAmount(int money) {
        Cost cost = new Cost(money);
        Assertions.assertEquals(1, cost.calculateLottoAmount());
    }

    @Test
    void calculateResult() {
        LottoGame lottoGame = new LottoGame(1000, testNumberGenerator);

        Lotto inputLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winningLotto = new WinningLotto(inputLotto, bonusNumber);
        List<LottoRank> lottoRanks = lottoGame.calculateResult(winningLotto);

        Assertions.assertEquals(LottoRank.FIRST, lottoRanks.get(0));
    }
}
