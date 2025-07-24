package mission2.car;

import lombok.Getter;
import mission2.part.CarType;
import mission2.part.Engine;

public class SuvConstraintHandler implements ConstraintHandler {

    @Getter
    private final String errorMessage = "SUV에는 TOYOTA엔진 사용 불가";

    @Override
    public boolean isInvalid(Car car) {
        if (car.getType() != CarType.SUV)
            return false;
        
        return car.getEngine() == Engine.TOYOTA;
    }
}
