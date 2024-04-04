package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int VALID_LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        numbers = lottoNumbers;
    }

    public static Lotto fromNumberList(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != VALID_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
        }

        int uniqueNumbersCount = new HashSet<>(lottoNumbers).size();
        if (uniqueNumbersCount != VALID_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("중복된 로또 숫자가 존재합니다.");
        }
    }

    public int getMatchCount(Lotto otherLotto) {
        List<LottoNumber> otherNumbers = otherLotto.getNumbers();

        return (int) numbers.stream()
                .filter(otherNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
