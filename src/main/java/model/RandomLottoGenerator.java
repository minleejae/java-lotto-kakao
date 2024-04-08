package model;

import model.Lotto;
import model.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {

    public static final int VALID_MIN_LOTTO_NUMBER = 1;
    public static final int VALID_MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBERS_COUNT = 6;

    public Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = VALID_MIN_LOTTO_NUMBER; i <= VALID_MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        numbers = numbers.subList(0, LOTTO_NUMBERS_COUNT);
        Collections.sort(numbers);

        return Lotto.fromNumberList(numbers);
    }
}
