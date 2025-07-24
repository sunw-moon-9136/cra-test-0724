package mission2.entity.step;

import mission2.entity.car.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RunTestStepTest {

    RunTestStep runTestStep = RunTestStep.getInstance();

    @Test
    void initPart() {
        // TODO: 삭제 필요
        runTestStep.initPart(null);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void isValidRange_then_true(int answer) {
        assertThat(runTestStep.isValidRange(answer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void isValidRange_then_false(int answer) {
        assertThat(runTestStep.isValidRange(answer)).isFalse();
    }

    @Test
    void doProcess_when_answer_is_1() {
        // given
        Car car = mock(Car.class);

        // when
        runTestStep.doProcess(car, 1);

        // then
        verify(car, times(1)).runProducedCar();
        verify(car, times(0)).testProducedCar();
    }

    @Test
    void doProcess_when_answer_is_2() {
        // given
        Car car = mock(Car.class);

        // when
        runTestStep.doProcess(car, 2);

        // then
        verify(car, times(0)).runProducedCar();
        verify(car, times(1)).testProducedCar();
    }

    @Test
    void doProcess_when_invalid_answer() {
        // given
        Car car = mock(Car.class);

        // when
        runTestStep.doProcess(car, 3);

        // then
        verify(car, times(0)).runProducedCar();
        verify(car, times(0)).testProducedCar();
    }
}