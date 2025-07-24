package mission2.car;

import lombok.Getter;
import mission2.part.CarType;
import mission2.part.Engine;

public class TruckEngineConstraintHandler implements ConstraintHandler {

    @Getter
    private final String errorMessage = "Truck에는 WIA엔진 사용 불가";

    @Override
    public boolean isInvalid(Car car) {
        return car.getType() == CarType.Truck && car.getEngine() == Engine.WIA;
    }
}
