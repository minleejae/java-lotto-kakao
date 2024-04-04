package generator;

import model.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {

    public Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        numbers = numbers.subList(0, 6);
        Collections.sort(numbers);

        return Lotto.fromNumberList(numbers);
    }
}
