package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        return splitAndTrimValidate(input, ",");
    }

    public List<String> splitAndTrimValidate(String line, String delimiter) {
        List<String> tokens = Arrays.stream(line.split(Pattern.quote(delimiter), -1))
                .map(String::trim)
                .collect(Collectors.toList());
        validateNotBlank(tokens);
        return tokens;
    }

    private void validateNotBlank(List<String> tokens) {
        if (tokens.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("[ERROR] 과정과 미션 입력");
        }
    }


    public String readReMatchingAnswer() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        return Console.readLine();
    }

}
