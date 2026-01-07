package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.util.Parser;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String FUNCTION_TITLE = "기능을 선택하세요.";
    private static final String FUNCTION_MATCHING = "1. 페어 매칭";
    private static final String FUNCTION_QUERY = "2. 페어 조회";
    private static final String FUNCTION_CLEAR = "3. 페어 초기화";
    private static final String FUNCTION_QUIT = "Q. 종료";


    public String readFunction() {
        System.out.println(FUNCTION_TITLE);
        System.out.println(FUNCTION_MATCHING);
        System.out.println(FUNCTION_QUERY);
        System.out.println(FUNCTION_CLEAR);
        System.out.println(FUNCTION_QUIT);
        return Console.readLine().trim();
    }

    public List<String> readCourseAndMission() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        return Parser.splitAndTrimValidate(Console.readLine(), DELIMITER);
    }

    public String readReMatchingAnswer() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까? (네/아니오)");
        return Console.readLine().trim();
    }
}
