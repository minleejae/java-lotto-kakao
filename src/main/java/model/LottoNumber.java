package model;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
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
