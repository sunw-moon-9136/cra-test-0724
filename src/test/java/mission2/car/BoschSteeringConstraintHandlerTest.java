package mission2.car;

import mission2.part.Brake;
import mission2.part.Steering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoschSteeringConstraintHandlerTest {

    BoschSteeringConstraintHandler handler = new BoschSteeringConstraintHandler();
    Car car = new Car();

    @BeforeEach
    void setUp() {
        car.setBrake(Brake.BOSCH);
    }

    @Test
    void isInvalid_when_not_bosch() {
        car.setBrake(Brake.MANDO);
        assertThat(handler.isInvalid(car)).isFalse();
    }

    @Test
    void isInvalid_when_mando() {
        car.setSteering(Steering.MOBIS);
        assertThat(handler.isInvalid(car)).isTrue();
    }

    @Test
    void isInvalid_when_not_mando() {
        car.setSteering(Steering.BOSCH);
        assertThat(handler.isInvalid(car)).isFalse();
    }
}