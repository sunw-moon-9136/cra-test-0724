package mission2.entity.car;

import lombok.Setter;
import mission2.entity.part.Brake;
import mission2.entity.part.CarType;
import mission2.entity.part.Engine;
import mission2.entity.part.Steering;

@Setter
public class Car {

    private CarType type;
    private Engine engine;
    private Brake brake;
    private Steering steering;

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
        if (isContinentalConstraint()) printFailMessage("Sedan에는 Continental제동장치 사용 불가");
        else if (isToyotaConstraint()) printFailMessage("SUV에는 TOYOTA엔진 사용 불가");
        else if (isWiaConstraint()) printFailMessage("Truck에는 WIA엔진 사용 불가");
        else if (isMandoConstraint()) printFailMessage("Truck에는 Mando제동장치 사용 불가");
        else if (isBoschConstraint()) printFailMessage("Bosch제동장치에는 Bosch조향장치 이외 사용 불가0");
        else System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private boolean isValidCheck() {
        if (isContinentalConstraint()) return false;
        if (isToyotaConstraint()) return false;
        if (isWiaConstraint()) return false;
        if (isMandoConstraint()) return false;
        return !isBoschConstraint();
    }

    private boolean isBrokenEngine() {
        return engine == Engine.BROKEN;
    }

    private void printRunnableCarInfo() {
        System.out.printf("Car Type : %s\n", type.name());
        System.out.printf("Engine   : %s\n", engine.name());
        System.out.printf("Brake    : %s\n", brake.name());
        System.out.printf("Steering : %s\n", steering.name());
        System.out.println("자동차가 동작됩니다.");
    }

    private boolean isContinentalConstraint() {
        return type == CarType.Sedan && brake == Brake.CONTINENTAL;
    }

    private boolean isToyotaConstraint() {
        return type == CarType.SUV && engine == Engine.TOYOTA;
    }

    private boolean isWiaConstraint() {
        return type == CarType.Truck && engine == Engine.WIA;
    }

    private boolean isMandoConstraint() {
        return type == CarType.Truck && brake == Brake.MANDO;
    }

    private boolean isBoschConstraint() {
        return brake == Brake.BOSCH && steering != Steering.BOSCH;
    }

    private void printFailMessage(String message) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(message);
    }

    public void clear() {
        type = null;
        engine = null;
        brake = null;
        steering = null;
    }
}
