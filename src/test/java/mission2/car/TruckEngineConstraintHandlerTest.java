package mission2.car;

import mission2.part.CarType;
import mission2.part.Engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TruckEngineConstraintHandlerTest {

    TruckEngineConstraintHandler handler = new TruckEngineConstraintHandler();
    Car car = new Car();

    @BeforeEach
    void setUp() {
        car.setType(CarType.Truck);
    }

    @Test
    void isInvalid_when_mando() {
        car.setEngine(Engine.WIA);
        assertThat(handler.isInvalid(car)).isTrue();
    }

    @Test
    void isInvalid_when_not_mando() {
        car.setEngine(Engine.TOYOTA);
        assertThat(handler.isInvalid(car)).isFalse();
    }
}