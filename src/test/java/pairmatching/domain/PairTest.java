package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void 페어는_이름목록을_가지고_인원수와_존재여부를_판단할_수_있다() {
        Pair pair = new Pair(Arrays.asList("철수", "영희", "민수"));

        assertThat(pair.getCount()).isEqualTo(3);
        assertThat(pair.isExist("철수", "영희")).isTrue();
        assertThat(pair.isExist("철수", "없는이름")).isFalse();
        assertThat(pair.getNameByIdx(0)).isEqualTo("철수");
    }

    @Test
    void 이름목록은_방어적_복사로_외부에서_수정해도_원본에_영향이_없다() {
        Pair pair = new Pair(Arrays.asList("철수", "영희"));

        List<String> copied = pair.getNames();
        copied.add("민수");

        assertThat(pair.getNames()).containsExactly("철수", "영희");
    }
}
