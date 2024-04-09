package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

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

    public Lottos generateManualAndAutoLottos(List<List<Integer>> manualLottoNumbers, int autoLottoAmount) {
        Lottos manualLottos = generateManualLottos(manualLottoNumbers);
        Lottos autoLottos = generateAutoLottos(autoLottoAmount);

        List<Lotto> mergedLottos = new ArrayList<>();
        mergedLottos.addAll(manualLottos.getLottos());
        mergedLottos.addAll(autoLottos.getLottos());
        return new Lottos(mergedLottos);
    }

    public Lottos generateAutoLottos(int autoLottoAmount) {
        List<Lotto> lottos = IntStream.range(0, autoLottoAmount)
                .mapToObj(it -> generateLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public Lottos generateManualLottos(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> lottos = manualLottoNumbers.stream()
                .map(Lotto::fromNumberList)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }
}
