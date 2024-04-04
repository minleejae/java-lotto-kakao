package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        for (int i = LOTTO_NUMBER_LOWER_BOUND; i <= LOTTO_NUMBER_UPPER_BOUND; i++) {
            CACHE.add(new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    private LottoNumber(final int number) {
        this.lottoNumber = number;
    }

    public static LottoNumber valueOf(final int number) {
        validateLottoNumber(number);
        LottoNumber lottoNumber = CACHE.get(number - 1);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    private static void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < LOTTO_NUMBER_LOWER_BOUND || lottoNumber > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException("로또에 사용할 수 없는 숫자입니다. lottoNumber: " + lottoNumber);
        }
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.getLottoNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.getLottoNumber();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(lottoNumber);
    }
}
