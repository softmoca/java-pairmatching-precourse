package pairmatching.domain;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String name) {
        for (Course course : values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 과정");
    }
}
