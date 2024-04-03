package model;

import generator.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int amount, NumberGenerator numberGenerator) {
        lottos = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto(numberGenerator));
        }
    }

    public Lottos(Lottos other) {
        lottos = new ArrayList<>(other.lottos);
    }

    public List<Lotto> getLottos() {
        return new Lottos(this).lottos;
    }
}
