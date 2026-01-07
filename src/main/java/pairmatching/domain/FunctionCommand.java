package pairmatching.domain;

import java.util.Arrays;

public enum FunctionCommand {
    MATCHING("1"),
    QUERY("2"),
    CLEAR("3"),
    QUIT("Q");

    private static final String INVALID_FUNCTION_COMMAND_ERROR =
            "[ERROR] 유효하지 않은 기능 입력입니다.";

    private final String code;

    FunctionCommand(String code) {
        this.code = code;
    }

    public static FunctionCommand from(String code) {
        return Arrays.stream(values())
                .filter(command -> command.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_FUNCTION_COMMAND_ERROR)
                );
    }

    public String getCode() {
        return code;
    }
}
