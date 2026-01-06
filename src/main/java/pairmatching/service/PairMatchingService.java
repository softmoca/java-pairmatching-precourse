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

    public PairMatchingService(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public List<Pair> match(MatchingKey matchingKey, MatchingHistory matchingHistory) {

        for (int i = 0; i < 3; i++) {
            List<String> names = Randoms.shuffle(nameRepository.getNames(matchingKey.getCourse()));
            List<Pair> pairs = createPairs(names);
            if (validePair(pairs, matchingHistory, matchingKey)) {
                matchingHistory.save(matchingKey, pairs);
                return pairs;
            }
        }
        throw new IllegalArgumentException("[ERROR] 매칭 3회 모두 실패");
    }

    private boolean validePair(List<Pair> newPairs, MatchingHistory matchingHistory, MatchingKey matchingKey) {
        List<Pair> existPairs = matchingHistory.findSameLevelPair(matchingKey);
        for (Pair existPair : existPairs) {
            if (isValidPair(newPairs, existPair)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidPair(List<Pair> newPairs, Pair existPair) {
        for (Pair newPair : newPairs) {
            int newPairCount = newPair.getCount();
            if (newPairCount == 2 && (existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(1)))) {
                return false;
            }
            if (isValid3NewPair(existPair, newPair)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValid3NewPair(Pair existPair, Pair newPair) {
        if (existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(1))) {
            return false;
        }
        if (existPair.isExist(newPair.getNameByIdx(0), newPair.getNameByIdx(2))) {
            return false;
        }
        if (existPair.isExist(newPair.getNameByIdx(1), newPair.getNameByIdx(2))) {
            return false;
        }
        return true;
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
