package view;

import model.Lotto;
import model.LottoNumber;
import model.LottoRank;
import model.Lottos;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public int requestManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = sc.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestCost();
        }
    }

    public void displayLottoAmount(int manualLottoAmount, int autoLottoAmount) {
        System.out.println("수동으로 " + manualLottoAmount + "장, 자동으로 " + autoLottoAmount + "개를 구매했습니다.");
    }

    public void displayLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String numbers = lotto.getNumbers()
                    .stream()
                    .map(LottoNumber::getLottoNumber)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "[", "]"));

            System.out.println(numbers);
        }
        System.out.println();
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return requestLottoNumbers();
    }

    public List<List<Integer>> requestManualLottoNumbers(int amount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, amount)
                .mapToObj(i -> requestLottoNumbers())
                .collect(Collectors.toList());
    }

    public List<Integer> requestLottoNumbers() {
        String input = sc.nextLine();

        try {
            return Arrays.stream(input.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return requestLottoNumbers();
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

    public void displayStatistics(Map<LottoRank, Long> lottoRanks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<LottoRank> ranks = Arrays.asList(LottoRank.values());
        Collections.reverse(ranks);

        for (LottoRank lottoRank : ranks) {
            displayRankStatistic(lottoRank, lottoRanks.getOrDefault(lottoRank, 0L));
        }
    }

    private static void displayRankStatistic(LottoRank lottoRank, Long count) {
        if (lottoRank == LottoRank.FAIL) {
            return;
        }

        System.out.println(
                lottoRank.getMatchCount() + "개 일치" + (lottoRank == LottoRank.SECOND ? ", 보너스 볼 일치" : "")
                        + "(" + lottoRank.getPrize() + "원) - " + count + "개");
    }

    public void displayProfit(Double profit) {
        System.out.println("총 수익률은 " + profit + "입니다.");
    }
}
