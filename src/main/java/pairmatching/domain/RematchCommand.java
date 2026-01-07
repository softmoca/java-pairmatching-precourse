package pairmatching.domain;

import java.util.Arrays;

public enum RematchCommand {
    YES("네"),
    NO("아니오");

    private static final String INVALID_REMATCH_COMMAND_ERROR =
            "[ERROR] 유효하지 않은 재시도 입력입니다.";

    private final String name;

    RematchCommand(String name) {
        this.name = name;
    }

    public static RematchCommand from(String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_REMATCH_COMMAND_ERROR)
                );
    }

    public String getName() {
        return name;
    }
}
