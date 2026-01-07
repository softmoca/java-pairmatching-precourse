package pairmatching.domain;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private static final String INVALID_LEVEL_ERROR =
            "[ERROR] 존재하지 않는 레벨입니다.";

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level from(String name) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_LEVEL_ERROR)
                );
    }

    public String getName() {
        return name;
    }
}
