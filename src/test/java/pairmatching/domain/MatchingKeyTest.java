package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MatchingKeyTest {

    @Test
    void 입력_토큰으로_MatchingKey를_생성한다() {
        MatchingKey key = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));

        assertThat(key.getCourse()).isEqualTo(Course.BACKEND);
        assertThat(key.getLevel()).isEqualTo(Level.LEVEL1);
    }

    @Test
    void 입력_토큰이_3개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> MatchingKey.from(Arrays.asList("백엔드", "레벨1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 같은_과정과_레벨이면_동일과정레벨로_판단한다() {
        MatchingKey a = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));
        MatchingKey b = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "로또"));
        MatchingKey c = MatchingKey.from(Arrays.asList("프론트엔드", "레벨1", "로또"));

        assertThat(a.isSameCourseAndLevel(b)).isTrue();
        assertThat(a.isSameCourseAndLevel(c)).isFalse();
    }

    @Test
    void equals는_과정과_미션이_같을때만_true다() {
        MatchingKey a = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));
        MatchingKey same = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "자동차경주"));
        MatchingKey differentMission = MatchingKey.from(Arrays.asList("백엔드", "레벨1", "로또"));

        assertThat(a).isEqualTo(same);
        assertThat(a).isNotEqualTo(differentMission);
    }
}
