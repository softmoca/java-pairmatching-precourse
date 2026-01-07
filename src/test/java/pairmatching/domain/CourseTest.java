package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CourseTest {

    @ParameterizedTest
    @CsvSource({
            "백엔드, BACKEND",
            "프론트엔드, FRONTEND"
    })
    void 과정_이름으로_Course를_조회한다(String input, Course expected) {
        assertThat(Course.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"안드로이드 ", "프론트", "BACKEND"})
    void 존재하지_않는_과정이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> Course.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
