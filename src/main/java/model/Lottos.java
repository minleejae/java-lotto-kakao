package model;

import generator.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos of(List<List<Integer>> manualLottoNumbers, int autoLottoAmount, LottoGenerator lottoGenerator) {
        List<Lotto> lottos = Stream.concat(
                manualLottoNumbers.stream()
                        .map(Lotto::fromNumberList),
                IntStream.range(0, autoLottoAmount)
                        .mapToObj(i -> lottoGenerator.generateLotto())
        ).collect(Collectors.toList());

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
