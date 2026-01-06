package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.util.Parser;

public class InputView {
    public String readFunction() {

        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
        String input = Console.readLine();
        return input;
    }

    public List<String> readCourseAndMission() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        String input = Console.readLine();
        return Parser.splitAndTrimValidate(input, ",");
    }

    public String readReMatchingAnswer() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        return Console.readLine();
    }

}
