package generator;

import model.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    public List<LottoNumber> generateNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(new LottoNumber(i));
        }
        Collections.shuffle(numbers);

        numbers = numbers.subList(0, 6);
        Collections.sort(numbers);
        return numbers;
    }
}
