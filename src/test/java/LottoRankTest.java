import model.LottoRank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRankTest {

    @Test
    void testOfWithVariousMatchCounts() {
        assertEquals(LottoRank.FIRST, LottoRank.of(6, false));
        assertEquals(LottoRank.SECOND, LottoRank.of(5, true));
        assertEquals(LottoRank.THIRD, LottoRank.of(5, false));
        assertEquals(LottoRank.FOURTH, LottoRank.of(4, false));
        assertEquals(LottoRank.FIFTH, LottoRank.of(3, false));
        assertEquals(LottoRank.FAIL, LottoRank.of(2, false));
    }
}