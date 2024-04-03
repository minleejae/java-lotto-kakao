package model;

import java.util.List;

public class WinningLotto {
    private final Lotto inputLotto;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(List<Integer> winningLottoNumbers, int bonusNumber) {
        this(Lotto.fromNumberList(winningLottoNumbers), new LottoNumber(bonusNumber));
    }

    public WinningLotto(Lotto inputLotto, LottoNumber bonusNumber) {
        validationWinningLotto(inputLotto, bonusNumber);
        this.inputLotto = inputLotto;
        this.bonusLottoNumber = bonusNumber;
    }

    private void validationWinningLotto(Lotto inputLotto, LottoNumber bonusNumber) {
        if (inputLotto.contains(bonusNumber)){
            throw new IllegalArgumentException("보너스 숫자는 입력되지 않은 로또 숫자만 가능합니다.");
        }
    }

    public LottoRank makeLottoRank(Lotto lotto) {
        int matchCount = getMatchCount(lotto);
        boolean matchBonus = checkLottoContainsBonusNumber(lotto);
        return LottoRank.of(matchCount, matchBonus);
    }

    private int getMatchCount(Lotto lotto) {
        return inputLotto.getMatchCount(lotto);
    }

    private boolean checkLottoContainsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusLottoNumber);
    }
}
