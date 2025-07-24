package mission2.car;

import mission2.part.Brake;
import mission2.part.CarType;
import mission2.part.Engine;
import mission2.part.Steering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarTest {

    @Spy
    Car car;

    @BeforeEach
    void setUp() {
        reset(car);
        car.setType(CarType.Sedan);
        car.setEngine(Engine.TOYOTA);
        car.setBrake(Brake.BOSCH);
        car.setSteering(Steering.BOSCH);
    }

    @Nested
    class RunProducedCarTest {
        @Test
        void runProducedCar() {
            //when
            car.runProducedCar();

            //then
            verify(car, times(1)).printRunnableCarInfo();
        }

        @Test
        void runProducedCar_when_with_invalid_parts() {
            //given
            doReturn(false).when(car).isValidCheck();

            //when
            car.runProducedCar();

            //then
            verify(car, times(0)).printRunnableCarInfo();
        }

        @Test
        void runProducedCar_when_with_broken_engine() {
            //given
            car.setEngine(Engine.BROKEN);

            //when
            car.runProducedCar();

            //then
            verify(car, times(0)).printRunnableCarInfo();
        }
    }

    @Test
    void testProducedCar() {
    }

    @Test
    void clear() {
        //when
        car.clear();

        //then
        assertThat(car).extracting(Car::getType, Car::getEngine, Car::getBrake, Car::getSteering)
                .contains(null, null, null, null);
    }
}