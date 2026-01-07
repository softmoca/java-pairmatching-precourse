package pairmatching.view;

import java.util.List;
import pairmatching.domain.Pair;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    private static final String LINE_SEPARATOR = "#############################################";
    private static final String COURSE_LINE = "과정: 백엔드 | 프론트엔드";
    private static final String MISSION_TITLE = "미션:";
    private static final String LEVEL1_LINE = "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임";
    private static final String LEVEL2_LINE = "  - 레벨2: 장바구니 | 결제 | 지하철노선도";
    private static final String LEVEL3_LINE = "  - 레벨3: ";
    private static final String LEVEL4_LINE = "  - 레벨4: 성능개선 | 배포";
    private static final String LEVEL5_LINE = "  - 레벨5: ";

    public void printCourseAndMission() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(COURSE_LINE);
        System.out.println(MISSION_TITLE);
        System.out.println(LEVEL1_LINE);
        System.out.println(LEVEL2_LINE);
        System.out.println(LEVEL3_LINE);
        System.out.println(LEVEL4_LINE);
        System.out.println(LEVEL5_LINE);
        System.out.println(LINE_SEPARATOR);

    }


    public void printMatchingResult(List<Pair> pairList) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairList) {
            System.out.println(String.join(" : ", pair.getNames()));
        }
        System.out.print(NEW_LINE);

    }


    public void printError(String message) {
        System.out.println(message);
    }

}
