package mission2.car;

import mission2.part.Brake;
import mission2.part.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TruckBrakeConstraintHandlerTest {

    TruckBrakeConstraintHandler handler = new TruckBrakeConstraintHandler();
    Car car = new Car();

    @BeforeEach
    void setUp() {
        car.setType(CarType.Truck);
    }

    @Test
    void isInvalid_when_mando() {
        car.setBrake(Brake.MANDO);
        assertThat(handler.isInvalid(car)).isTrue();
    }

    @Test
    void isInvalid_when_not_mando() {
        car.setBrake(Brake.BOSCH);
        assertThat(handler.isInvalid(car)).isFalse();
    }
}