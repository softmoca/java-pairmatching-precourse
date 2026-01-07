package pairmatching.util;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserTest {

    @Nested
    @DisplayName("splitAndTrimValidate 테스트")
    class SplitAndTrimValidateTest {

        @DisplayName("쉼표로 구분된 문자열 분리 성공")
        @Test
        void 쉼표로_분리_성공() {
            List<String> result = Parser.splitAndTrimValidate("a,b,c", ",");

            assertThat(result).containsExactly("a", "b", "c");
        }

        @DisplayName("공백 포함된 문자열도 trim하여 분리")
        @Test
        void 공백_포함_trim() {
            List<String> result = Parser.splitAndTrimValidate(" a , b , c ", ",");

            assertThat(result).containsExactly("a", "b", "c");
        }

        @DisplayName("하이픈으로 구분된 문자열 분리")
        @Test
        void 하이픈으로_분리() {
            List<String> result = Parser.splitAndTrimValidate("apple-banana-cherry", "-");

            assertThat(result).containsExactly("apple", "banana", "cherry");
        }

        @DisplayName("단일 값도 리스트로 반환")
        @Test
        void 단일_값_처리() {
            List<String> result = Parser.splitAndTrimValidate("single", ",");

            assertThat(result).containsExactly("single");
        }

        @DisplayName("빈 문자열이 포함되면 예외")
        @Test
        void 빈_문자열_포함_예외() {
            assertThatThrownBy(() -> Parser.splitAndTrimValidate("a,,c", ","))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("유효하지 않은");
        }

        @DisplayName("공백만 있는 토큰은 예외")
        @Test
        void 공백만_있는_토큰_예외() {
            assertThatThrownBy(() -> Parser.splitAndTrimValidate("a,  ,c", ","))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("유효하지 않은");
        }

        @DisplayName("여러 개의 빈 값이 있어도 예외")
        @Test
        void 여러_빈_값_예외() {
            // Pattern.quote()와 limit=-1로 trailing empty 유지되어 예외 발생
            assertThatThrownBy(() -> Parser.splitAndTrimValidate("a,,,", ","))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("유효하지 않은");
        }

        @DisplayName("다양한 구분자 테스트")
        @ParameterizedTest
        @CsvSource({
                "'a|b|c', '|', 3",   // Pattern.quote()로 파이프 escape
                "'x:y:z', ':', 3",
                "'1;2;3', ';', 3"
        })
        void 다양한_구분자(String input, String delimiter, int expectedSize) {
            List<String> result = Parser.splitAndTrimValidate(input, delimiter);

            assertThat(result).hasSize(expectedSize);
        }

        @DisplayName("숫자 문자열도 분리 가능")
        @Test
        void 숫자_문자열_분리() {
            List<String> result = Parser.splitAndTrimValidate("1,2,3,4,5", ",");

            assertThat(result).containsExactly("1", "2", "3", "4", "5");
        }

        @DisplayName("긴 문자열도 분리 가능")
        @Test
        void 긴_문자열_분리() {
            String input = "apple,banana,cherry,date,elderberry,fig,grape";
            List<String> result = Parser.splitAndTrimValidate(input, ",");

            assertThat(result).hasSize(7);
            assertThat(result).contains("apple", "grape");
        }
    }

}
