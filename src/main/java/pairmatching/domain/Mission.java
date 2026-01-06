package pairmatching.domain;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    NUMBERS_BASEBALL_GAME("숫자야구게임", Level.LEVEL1),
    SHOPPING_CART("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAYMAP("지하철노선도", Level.LEVEL2),
    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL4),
    DEPLOY("배포", Level.LEVEL4);


    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission from(String name, Level level) {
        for (Mission mission : values()) {
            if (mission.name.equals(name) && mission.level == level) {
                return mission;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션");
    }

    public Level getLevel() {
        return level;
    }
}
