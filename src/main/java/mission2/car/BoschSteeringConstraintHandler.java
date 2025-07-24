package mission2.car;

import lombok.Getter;
import mission2.part.Brake;
import mission2.part.Steering;

public class BoschSteeringConstraintHandler implements ConstraintHandler {

    @Getter
    private final String errorMessage = "Bosch제동장치에는 Bosch조향장치 이외 사용 불가";

    @Override
    public boolean isInvalid(Car car) {
        return car.getBrake() == Brake.BOSCH && car.getSteering() != Steering.BOSCH;
    }
}
