package pairmatching.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import pairmatching.domain.Pair;

public class OutputView {
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.KOREA);
    private static final String NEW_LINE = System.lineSeparator();


    public void printCourseAndMission() {//TODO
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
        System.out.println("  - 레벨2: 장바구니 | 결제 | 지하철노선도");
        System.out.println("  - 레벨3: ");
        System.out.println("  - 레벨4: 성능개선 | 배포");
        System.out.println("  - 레벨5: ");
        System.out.println("#############################################");

    }

    public void printMatchingResult(List<Pair> pairList) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairList) {
            System.out.println(String.join(" : ", pair.getNames()));
        }
        System.out.println();

    }


    public void printError(String message) {
        System.out.println(message);
    }

    public String formatNumber(int number) {
        return NUMBER_FORMAT.format(number);
    }

}
