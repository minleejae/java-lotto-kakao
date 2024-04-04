package model;

import generator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int amount, LottoGenerator lottoGenerator) {
        lottos = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
    }

    public Lottos(Lottos other) {
        lottos = new ArrayList<>(other.lottos);
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> rottoRanks = lottos.stream()
                .map(winningLotto::makeLottoRank)
                .collect(Collectors.toList());

        return new LottoResult(rottoRanks);
    }

    public List<Lotto> getLottos() {
        return new Lottos(this).lottos;
    }
}
