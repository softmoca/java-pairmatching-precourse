package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.util.FileLoader;

public class NameRepository {
    private static final String BACKEND_FILE = "backend-crew.md";
    private static final String FRONTEND_FILE = "frontend-crew.md";

    private final List<String> backendNames;
    private final List<String> frontendNames;

    public NameRepository(FileLoader fileLoader) {
        backendNames = new ArrayList<>();
        frontendNames = new ArrayList<>();
        load(fileLoader);
    }

    private void load(FileLoader fileLoader) {
        backendNames.addAll(fileLoader.readLines(BACKEND_FILE));
        frontendNames.addAll(fileLoader.readLines(FRONTEND_FILE));
    }

    public List<String> getNames(Course course) {
        if (course == Course.BACKEND) {
            return new ArrayList<>(backendNames);
        }

        return new ArrayList<>(frontendNames);
    }


}
