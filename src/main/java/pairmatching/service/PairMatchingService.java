package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingKey;
import pairmatching.domain.Pair;
import pairmatching.repository.NameRepository;

public class PairMatchingService {

    private final NameRepository nameRepository;
    private final MatchingHistory matchingHistory;

    public PairMatchingService(NameRepository nameRepository, MatchingHistory matchingHistory) {
        this.nameRepository = nameRepository;
        this.matchingHistory = matchingHistory;
    }

    public List<Pair> match(MatchingKey matchingKey) {

        for (int i = 0; i < 3; i++) {
            List<String> names = Randoms.shuffle(nameRepository.getNames(matchingKey.getCourse()));
            List<Pair> pairs = createPairs(names);
            if (isValidMatching(pairs, matchingKey)) {
                matchingHistory.save(matchingKey, pairs);
                return pairs;
            }
        }
        throw new IllegalArgumentException("[ERROR] 매칭 3회 모두 실패");
    }

    private boolean isValidMatching(List<Pair> newPairs, MatchingKey matchingKey) {
        List<Pair> existPairs = matchingHistory.findPairsBySameCourseAndLevel(matchingKey);
        for (Pair existPair : existPairs) {
            if (hasAnyOverlap(existPair, newPairs)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasAnyOverlap(Pair existPair, List<Pair> newPairs) {
        for (Pair newPair : newPairs) {
            if (hasOverlapBetween(existPair, newPair)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasOverlapBetween(Pair existPair, Pair newPair) {
        int count = newPair.getCount();
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (existPair.isExist(newPair.getNameByIdx(i), newPair.getNameByIdx(j))) {
                    return true;
                }
            }
        }
        return false;
    }


    private List<Pair> createPairs(List<String> names) {
        List<Pair> pairs = new ArrayList<>();
        int totalCount = names.size();

        for (int i = 0; i < totalCount - 1; i += 2) {
            Pair pair = createPair(names, totalCount, i);
            pairs.add(pair);
        }
        return pairs;
    }

    private Pair createPair(List<String> names, int totalCount, int i) {
        List<String> pairNames = new ArrayList<>();
        pairNames.add(names.get(i));
        pairNames.add(names.get(i + 1));

        if (isLastOdd(totalCount, i)) {
            pairNames.add(names.get(i + 2));
        }

        return new Pair(pairNames);
    }

    private boolean isLastOdd(int totalCount, int i) {
        return (totalCount - 3) == i;
    }

}
