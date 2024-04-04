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

    public List<LottoRank> calculateResult(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::makeLottoRank)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return new Lottos(this).lottos;
    }
}
