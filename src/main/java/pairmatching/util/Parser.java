package pairmatching.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    public static List<String> splitAndTrimValidate(String line, String delimiter) {
        List<String> tokens = Arrays.stream(line.split(Pattern.quote(delimiter), -1))
                .map(String::trim)
                .collect(Collectors.toList());
        validateNotBlank(tokens);
        return tokens;
    }

    private static void validateNotBlank(List<String> tokens) {
        if (tokens.stream().anyMatch(String::isEmpty)) {

            throw new IllegalArgumentException("[ERROR] 유효하지 않은 값이 있습니다.");
        }
    }

}
