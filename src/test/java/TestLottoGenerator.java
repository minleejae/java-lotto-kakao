import generator.LottoGenerator;
import model.Lotto;

import java.util.List;

public class TestLottoGenerator implements LottoGenerator {

    private final List<List<Integer>> candidates;
    private int currentIndex = 0;

    public TestLottoGenerator(List<List<Integer>> candidates) {
        this.candidates = candidates;
    }

    public Lotto generateLotto() {
        List<Integer> numbers = candidates.get(currentIndex++ % candidates.size());
        return Lotto.fromNumberList(numbers);
    }
}
