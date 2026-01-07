package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FunctionCommandTest {

    @ParameterizedTest
    @CsvSource({
            "1, MATCHING",
            "2, QUERY",
            "3, CLEAR",
            "Q, QUIT"
    })
    void 코드로_기능을_조회한다(String input, FunctionCommand expected) {
        assertThat(FunctionCommand.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0", "4", "q", "QUIT"})
    void 유효하지_않은_코드면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> FunctionCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
