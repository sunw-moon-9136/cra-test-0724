package mission2.car;

import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import mission2.part.Brake;
import mission2.part.CarType;
import mission2.part.Engine;
import mission2.part.Steering;

import java.util.List;

@VisibleForTesting
@Getter(AccessLevel.PROTECTED)
@Setter
public class Car {

    private CarType type;
    private Engine engine;
    private Brake brake;
    private Steering steering;

    @VisibleForTesting
    @Setter(AccessLevel.PACKAGE)
    private List<ConstraintHandler> handlers =
            List.of(new SedanConstraintHandler(),
                    new SuvConstraintHandler(),
                    new TruckEngineConstraintHandler(),
                    new TruckBrakeConstraintHandler(),
                    new BoschSteeringConstraintHandler());

    public void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }

        if (isBrokenEngine()) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        printRunnableCarInfo();
    }

    public void testProducedCar() {
        for (var handler : handlers) {
            if (handler.isInvalid(this)) {
                printFailMessage(handler.getErrorMessage());
                return;
            }
        }
        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    public void clear() {
        type = null;
        engine = null;
        brake = null;
        steering = null;
    }

    @VisibleForTesting
    boolean isValidCheck() {
        for (var handler : handlers) {
            if (handler.isInvalid(this)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBrokenEngine() {
        return engine == Engine.BROKEN;
    }

    @VisibleForTesting
    void printRunnableCarInfo() {
        System.out.printf("Car Type : %s\n", type.name());
        System.out.printf("Engine   : %s\n", engine.name());
        System.out.printf("Brake    : %s\n", brake.name());
        System.out.printf("Steering : %s\n", steering.name());
        System.out.println("자동차가 동작됩니다.");
    }


    private void printFailMessage(String message) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(message);
    }
}
