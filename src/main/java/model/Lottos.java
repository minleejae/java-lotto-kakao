package model;

import generator.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos of(int amount, LottoGenerator lottoGenerator) {
        List<Lotto> lottos = IntStream.range(0, amount)
                .mapToObj(i -> lottoGenerator.generateLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<LottoRank> rottoRanks = lottos.stream()
                .map(winningLotto::makeLottoRank)
                .collect(Collectors.toList());

        return new LottoResult(rottoRanks);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
