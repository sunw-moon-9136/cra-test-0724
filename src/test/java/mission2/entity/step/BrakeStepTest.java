package mission2.entity.step;

import mission2.entity.part.Brake;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BrakeStepTest {

    BrakeStep brakeStep = BrakeStep.getInstance();

    @Test
    void initPart() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();
        car.setBrake(Brake.BOSCH);

        // when
        brakeStep.initPart(car);

        // then
        assertThat(car.getBrake()).isNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void isValidRange_then_true(int answer) {
        assertThat(brakeStep.isValidRange(answer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void isValidRange_then_false(int answer) {
        assertThat(brakeStep.isValidRange(answer)).isFalse();
    }

    @Test
    void doProcess() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();

        // when
        brakeStep.doProcess(car, 1);

        // then
        assertThat(car.getBrake()).isEqualTo(Brake.MANDO);
    }
}