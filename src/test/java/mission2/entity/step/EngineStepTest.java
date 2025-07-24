package mission2.entity.step;

import mission2.entity.part.Engine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EngineStepTest {

    EngineStep engineStep = EngineStep.getInstance();

    @Test
    void initPart() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();
        car.setEngine(Engine.GM);

        // when
        engineStep.initPart(car);

        // then
        assertThat(car.getEngine()).isNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void isValidRange_then_true(int answer) {
        assertThat(engineStep.isValidRange(answer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 5})
    void isValidRange_then_false(int answer) {
        assertThat(engineStep.isValidRange(answer)).isFalse();
    }

    @Test
    void doProcess() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();

        // when
        engineStep.doProcess(car, 1);

        // then
        assertThat(car.getEngine()).isEqualTo(Engine.GM);
    }
}