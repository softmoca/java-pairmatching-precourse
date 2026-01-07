package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MissionTest {

    @ParameterizedTest
    @CsvSource({
            "자동차경주, LEVEL1, CAR_RACING",
            "로또, LEVEL1, LOTTO",
            "숫자야구게임, LEVEL1, NUMBERS_BASEBALL_GAME",
            "장바구니, LEVEL2, SHOPPING_CART",
            "결제, LEVEL2, PAYMENT",
            "지하철노선도, LEVEL2, SUBWAYMAP",
            "성능개선, LEVEL4, PERFORMANCE_IMPROVEMENT",
            "배포, LEVEL4, DEPLOY"
    })
    void 미션_이름과_레벨로_Mission을_조회한다(String name, Level level, Mission expected) {
        assertThat(Mission.from(name, level)).isEqualTo(expected);
    }

    @Test
    void 미션이_같아도_레벨이_다르면_조회에_실패한다() {
        assertThatThrownBy(() -> Mission.from("자동차경주", Level.LEVEL2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 존재하지_않는_미션이면_예외가_발생한다() {
        assertThatThrownBy(() -> Mission.from("없는미션", Level.LEVEL1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
