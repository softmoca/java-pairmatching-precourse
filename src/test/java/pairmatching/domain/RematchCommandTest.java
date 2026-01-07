package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RematchCommandTest {

    @ParameterizedTest
    @CsvSource({
            "네, YES",
            "아니오, NO"
    })
    void 입력_문자열로_재매칭_명령을_조회한다(String input, RematchCommand expected) {
        assertThat(RematchCommand.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"예", "아니요", "YES", "NO"})
    void 유효하지_않은_입력이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> RematchCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
