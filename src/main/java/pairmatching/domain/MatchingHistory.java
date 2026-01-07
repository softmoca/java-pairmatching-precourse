package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingHistory {

    private final Map<MatchingKey, List<Pair>> matchingMap;

    public MatchingHistory() {
        this.matchingMap = new HashMap<>();
    }

    public List<Pair> getPairsByKey(MatchingKey key) {
        return matchingMap.getOrDefault(key, Collections.emptyList());
    }

    public boolean contains(MatchingKey matchingKey) {
        return matchingMap.containsKey(matchingKey);
    }

    public List<Pair> findPairsBySameCourseAndLevel(MatchingKey target) {
        List<Pair> result = new ArrayList<>();
        for (Map.Entry<MatchingKey, List<Pair>> entry : matchingMap.entrySet()) {
            if (entry.getKey().isSameCourseAndLevel(target)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    public void save(MatchingKey matchingKey, List<Pair> pairs) {
        matchingMap.put(matchingKey, pairs);
    }

    public void clear() {
        matchingMap.clear();
    }
}
