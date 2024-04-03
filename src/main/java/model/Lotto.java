package model;

import generator.NumberGenerator;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(NumberGenerator numberGenerator) {
        this(numberGenerator.generateNumbers());
    }

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        numbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
        }

        int uniqueNumbersCount = new HashSet<>(lottoNumbers).size();
        if (uniqueNumbersCount != 6) {
            throw new IllegalArgumentException("중복된 로또 숫자가 존재합니다.");
        }
    }

    public int getMatchCount(List<LottoNumber> winnerNumbers) {
        return (int) numbers.stream()
                .filter(winnerNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
