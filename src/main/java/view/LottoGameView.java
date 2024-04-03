package view;

import model.Lotto;
import model.LottoRank;
import model.Lottos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoGameView {

    private final Scanner sc;

    public LottoGameView() {
        sc = new Scanner(System.in);
    }

    public int requestCost() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = sc.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestCost();
        }
    }

    public void displayLottoAmount(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

    public void displayLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
        System.out.println();
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();

        try {
            // TODO: 1부터 45까지 중복되지 않은 수 있는지 확인
            return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestWinningNumbers();
        }
    }

    public int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = sc.nextLine();
        System.out.println();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestBonusNumber();
        }
    }

    public void displayStatistics(Map<LottoRank, Integer> lottoRanks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<LottoRank> ranks = Arrays.asList(LottoRank.values());
        Collections.reverse(ranks);

        for (LottoRank lottoRank : ranks) {
            displayStatistic(lottoRanks, lottoRank);
        }
    }

    private static void displayStatistic(Map<LottoRank, Integer> lottoRanks, LottoRank lottoRank) {
        if (lottoRank == LottoRank.FAIL) {
            return;
        }

        int count = lottoRanks.getOrDefault(lottoRank, 0);
        System.out.println(
            lottoRank.getMatchCount() + "개 일치" + (lottoRank.isMatchBonus() ? ", 보너스 볼 일치" : "")
                + "(" + lottoRank.getPrize() + "원) - " + count + "개");
    }

    public void displayProfit(Double profit) {
        System.out.println("총 수익률은 " + profit + "입니다.");
    }
}
