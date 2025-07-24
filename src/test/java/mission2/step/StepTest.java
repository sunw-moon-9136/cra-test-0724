package mission2.step;

import mission2.car.Car;
import mission2.part.Brake;
import mission2.part.CarType;
import mission2.part.Engine;
import mission2.part.Steering;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StepTest {

    @Spy
    Step step;

    // given
    Car car = new Car();

    @Test
    void process_when_answer_is_zero() {
        // when
        step.process(car, 0);

        // then
        verify(step, times(0)).doProcess(any(), anyInt());
    }

    @Test
    void process_when_answer_is_not_zero() {
        // given
        doNothing().when(step).doProcess(any(), anyInt());

        // when
        step.process(car, 1);

        // then
        verify(step, times(1)).doProcess(any(), anyInt());
    }

    @Test
    void clearConsoleOut() {
        assertThatNoException().isThrownBy(() -> step.clearConsoleOut());
    }

    public static class CarForTest extends Car {
        @Override
        protected CarType getType() {
            return super.getType();
        }

        @Override
        protected Engine getEngine() {
            return super.getEngine();
        }

        @Override
        protected Brake getBrake() {
            return super.getBrake();
        }

        @Override
        protected Steering getSteering() {
            return super.getSteering();
        }
    }
}