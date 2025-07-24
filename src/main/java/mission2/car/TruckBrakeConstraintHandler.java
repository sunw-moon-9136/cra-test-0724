package mission2.car;

import lombok.Getter;
import mission2.part.Brake;
import mission2.part.CarType;

public class TruckBrakeConstraintHandler implements ConstraintHandler {

    @Getter
    private final String errorMessage = "Truck에는 Mando제동장치 사용 불가";

    @Override
    public boolean isInvalid(Car car) {
        if (car.getType() != CarType.Truck)
            return false;
        
        return car.getBrake() == Brake.MANDO;
    }
}
