package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingHistory {

    private final Map<MatchingKey, List<Pair>> matchingMap;

    public MatchingHistory() {
        this.matchingMap = new HashMap<>();
    }

    public List<Pair> getPairsByKey(MatchingKey matchingKey) {
        return matchingMap.get(matchingKey);
    }

    public boolean isExist(MatchingKey matchingKey) {
        return matchingMap.containsKey(matchingKey);
    }

    public List<Pair> findSameLevelPair(MatchingKey matchingKey) {
        List<Pair> existPairs = new ArrayList<>();
        for (MatchingKey key : matchingMap.keySet()) {
            if (key.getCourse() == matchingKey.getCourse() &&
                    key.getLevel() == matchingKey.getLevel()
            ) {
                existPairs.addAll(matchingMap.get(matchingKey));
            }

        }
        return existPairs;

    }

    public void save(MatchingKey matchingKey, List<Pair> pairs) {
        matchingMap.put(matchingKey, pairs);
    }


    public void clear() {
        matchingMap.clear();
    }
}
