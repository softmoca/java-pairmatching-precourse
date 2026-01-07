package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    NUMBERS_BASEBALL_GAME("숫자야구게임", Level.LEVEL1),
    SHOPPING_CART("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAYMAP("지하철노선도", Level.LEVEL2),
    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL4),
    DEPLOY("배포", Level.LEVEL4);

    private static final String INVALID_MISSION_ERROR =
            "[ERROR] 존재하지 않는 미션입니다.";

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission from(String name, Level level) {
        return Arrays.stream(values())
                .filter(mission ->
                        mission.name.equals(name) &&
                                mission.level == level
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_MISSION_ERROR)
                );
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
