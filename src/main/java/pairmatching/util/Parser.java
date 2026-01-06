package pairmatching.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    public static int parsePositiveNumber(String value) {
        try {
            int num = Integer.parseInt(value);
            if (num <= 0) {
                throw new IllegalArgumentException("[ERROR] ");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 올바르지 않습니다.");
        }
    }

    public static LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 날짜 형식이 올바르지 않습니다.");
        }
    }
}
