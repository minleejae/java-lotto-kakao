import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    private TestNumberGenerator testNumberGenerator;

    @BeforeEach
    void setUp() {
        testNumberGenerator = new TestNumberGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void lottoCreationWithNumberGeneratorTest() {
        Lotto lotto = new Lotto(testNumberGenerator);
        assertNotNull(lotto);
        assertEquals(6, lotto.getNumbers().size());
    }

    @Test
    void lottoCreationFromNumberListTest() {
        Lotto lotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertNotNull(lotto);
        assertEquals(6, lotto.getNumbers().size());
    }

    @Test
    void validateLottoNumbersWithInvalidSizeTest() {
        assertThrows(IllegalArgumentException.class, () -> Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void validateLottoNumbersWithDuplicateNumbersTest() {
        assertThrows(IllegalArgumentException.class, () -> Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 5)));
    }

    @Test
    void getMatchCountTest() {
        Lotto firstLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto secondLotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 7, 8, 9));
        assertEquals(3, firstLotto.getMatchCount(secondLotto));
    }

    @Test
    void containsTest() {
        Lotto lotto = Lotto.fromNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertTrue(lotto.contains(new LottoNumber(1)));
        assertFalse(lotto.contains(new LottoNumber(7)));
    }
}