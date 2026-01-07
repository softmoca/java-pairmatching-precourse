package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class MatchingHistoryTest {

    @Test
    void 저장된_매칭은_key로_조회할_수_있다() {
        MatchingHistory history = new MatchingHistory();
        MatchingKey key = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));
        List<Pair> pairs = Collections.singletonList(
                new Pair(Arrays.asList("철수", "영희"))
        );

        history.save(key, pairs);

        assertThat(history.contains(key)).isTrue();
        assertThat(history.getPairsByKey(key)).hasSize(1);
    }

    @Test
    void 존재하지_않는_key_조회는_빈_리스트를_반환한다() {
        MatchingHistory history = new MatchingHistory();
        MatchingKey key = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));

        assertThat(history.getPairsByKey(key)).isEmpty();
    }

    @Test
    void 같은_과정과_레벨의_모든_페어를_찾는다() {
        MatchingHistory history = new MatchingHistory();

        MatchingKey a = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));
        MatchingKey b = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "로또"));
        MatchingKey c = MatchingKey.from(Arrays.asList("프론트엔드", "레벨1", "로또"));

        history.save(a, Collections.singletonList(new Pair(Arrays.asList("철수", "영희"))));
        history.save(b, Collections.singletonList(new Pair(Arrays.asList("민수", "지수"))));
        history.save(c, Collections.singletonList(new Pair(Arrays.asList("A", "B"))));

        List<Pair> result = history.findPairsBySameCourseAndLevel(a);

        assertThat(result).hasSize(2);
    }

    @Test
    void clear하면_매칭이력이_초기화된다() {
        MatchingHistory history = new MatchingHistory();
        MatchingKey key = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));

        history.save(key, Collections.singletonList(new Pair(Arrays.asList("철수", "영희"))));
        history.clear();

        assertThat(history.contains(key)).isFalse();
        assertThat(history.getPairsByKey(key)).isEmpty();
    }
}
