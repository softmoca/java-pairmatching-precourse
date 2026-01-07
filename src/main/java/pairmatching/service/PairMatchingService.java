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
            if (hasNoOverlapWith(existPair, newPairs) == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasNoOverlapWith(Pair existPair, List<Pair> newPairs) {
        for (Pair newPair : newPairs) {
            int newPairCount = newPair.getCount();

            if (newPairCount == 2 && existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(1))) {
                return false;
            }
            if (hasAnyOverlapInTriple(existPair, newPair)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasAnyOverlapInTriple(Pair existPair, Pair newPair) {
        if (existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(1))) {
            return true;
        }
        if (existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(2))) {
            return true;
        }
        if (existPair.isExist(newPair.getNameByIdx(1), newPair.getNameByIdx(2))) {
            return true;
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
