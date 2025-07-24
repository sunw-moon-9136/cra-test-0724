package mission2.car;

import lombok.Getter;
import mission2.part.CarType;
import mission2.part.Engine;

public class SuvConstraintHandler implements ConstraintHandler {

    @Getter
    private final String errorMessage = "SUV에는 TOYOTA엔진 사용 불가";

    @Override
    public boolean isInvalid(Car car) {
        return car.getType() == CarType.SUV && car.getEngine() == Engine.TOYOTA;
    }
}
