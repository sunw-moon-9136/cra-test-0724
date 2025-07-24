package mission2.step;

import mission2.part.Steering;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class SteeringStepTest {

    SteeringStep steeringStep = SteeringStep.getInstance();

    @Test
    void initPart() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();
        car.setSteering(Steering.BOSCH);

        // when
        steeringStep.initPart(car);

        // then
        assertThat(car.getEngine()).isNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void isValidRange_then_true(int answer) {
        assertThat(steeringStep.isValidRange(answer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void isValidRange_then_false(int answer) {
        assertThat(steeringStep.isValidRange(answer)).isFalse();
    }

    @Test
    void doProcess() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();

        // when
        steeringStep.doProcess(car, 1);

        // then
        assertThat(car.getSteering()).isEqualTo(Steering.BOSCH);
    }

    @Test
    void printMenu() {
        assertThatNoException().isThrownBy(() -> steeringStep.printMenu());
    }
}