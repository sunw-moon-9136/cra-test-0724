package mission2.step;

import mission2.part.CarType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class CarTypeStepTest {

    CarTypeStep carTypeStep = CarTypeStep.getInstance();

    @Test
    void initPart() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();
        car.setType(CarType.Sedan);

        // when
        carTypeStep.initPart(car);

        // then
        assertThat(car.getType()).isNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void isValidRange_then_true(int answer) {
        assertThat(carTypeStep.isValidRange(answer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4})
    void isValidRange_then_false(int answer) {
        assertThat(carTypeStep.isValidRange(answer)).isFalse();
    }

    @Test
    void doProcess() {
        // given
        StepTest.CarForTest car = new StepTest.CarForTest();

        // when
        carTypeStep.doProcess(car, 1);

        // then
        assertThat(car.getType()).isEqualTo(CarType.Sedan);
    }

    @Test
    void printMenu() {
        assertThatNoException().isThrownBy(() -> carTypeStep.printMenu());
    }
}