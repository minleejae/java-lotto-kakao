import generator.NumberGenerator;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {

    private final List<List<Integer>> candidates;
    private int currentIndex = 0;

    public TestNumberGenerator(List<List<Integer>> candidates) {
        this.candidates = candidates;
    }

    public List<Integer> generateNumbers() {
        return candidates.get(currentIndex++ % candidates.size());
    }
}
