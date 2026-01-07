package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LevelTest {

    @ParameterizedTest
    @CsvSource({
            "레벨1, LEVEL1",
            "레벨2, LEVEL2",
            "레벨3, LEVEL3",
            "레벨4, LEVEL4",
            "레벨5, LEVEL5"
    })
    void 레벨_이름으로_Level을_조회한다(String input, Level expected) {
        assertThat(Level.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"", "레벨0", "레벨6", "LEVEL1", "레벨 1"})
    void 존재하지_않는_레벨이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> Level.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
