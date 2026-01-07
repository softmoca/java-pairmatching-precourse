package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class MatchingKey {
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;
    private static final int INPUT_SIZE = 3;

    private static final String INVALID_INPUT_SIZE_ERROR =
            "[ERROR] 과정, 레벨, 미션을 순서대로 입력해야 합니다.";

    private final Course course;
    private final Mission mission;

    private MatchingKey(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    public static MatchingKey from(List<String> input) {
        validateInputSize(input);

        Course course = Course.from(input.get(COURSE_INDEX));
        Level level = Level.from(input.get(LEVEL_INDEX));
        Mission mission = Mission.from(input.get(MISSION_INDEX), level);

        return new MatchingKey(course, mission);
    }

    private static void validateInputSize(List<String> input) {
        if (input.size() != INPUT_SIZE) {
            throw new IllegalArgumentException(INVALID_INPUT_SIZE_ERROR);
        }
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return mission.getLevel();
    }

    public boolean isSameCourseAndLevel(MatchingKey other) {
        return this.course == other.course && this.getLevel() == other.getLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchingKey that = (MatchingKey) o;
        return course == that.course && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, mission);
    }
}
