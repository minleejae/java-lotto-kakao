import generator.NumberGenerator;
import model.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class TestNumberGenerator implements NumberGenerator {

    private final List<List<Integer>> candidates;
    private int currentIndex = 0;

    public TestNumberGenerator(List<List<Integer>> candidates) {
        this.candidates = candidates;
    }

    public List<LottoNumber> generateNumbers() {
        return candidates.get(currentIndex++ % candidates.size())
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
