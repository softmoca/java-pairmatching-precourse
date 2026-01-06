package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class MatchingKey {
    private final Course course;
    private final Mission mission;

    private MatchingKey(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    public static MatchingKey from(List<String> input) {
        validateSize(input);
        Course course = Course.from(input.get(0));
        Level level = Level.from(input.get(1));
        Mission mission = Mission.from(input.get(2), level);
        return new MatchingKey(course, mission);
    }

    private static void validateSize(List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 과정, 레벨, 미션 입력 3개");
        }
    }

    public Course getCourse() {
        return course;
    }


    public Level getLevel() {
        return mission.getLevel();
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
