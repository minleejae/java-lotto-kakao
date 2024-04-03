package model;

public class LottoGame {
    private final Lottos lottos;

    public LottoGame(Lottos lottos) {
        this.lottos = lottos;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        return new LottoResult(this.lottos.calculateResult(winningLotto));
    }

    public Lottos getLottos() {
        return lottos;
    }
}
