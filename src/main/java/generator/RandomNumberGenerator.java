package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    public List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        numbers = numbers.subList(0, 6);
        Collections.sort(numbers);
        return numbers;
    }
}
