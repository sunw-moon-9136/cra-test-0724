package mission2.entity.step;

import mission2.entity.car.Car;
import mission2.entity.part.CarType;
import mission2.util.ConsoleUtils;

public class CarTypeStep extends Step {
    private static CarTypeStep carTypeStep;

    private CarTypeStep(Step backStep, Step nextStep) {
        super(backStep, nextStep);
    }

    public static CarTypeStep getInstance() {
        if (carTypeStep == null) {
            carTypeStep = new CarTypeStep(carTypeStep, EngineStep.getInstance());
        }
        return carTypeStep;
    }

    @Override
    public void printMenu() {
        ConsoleUtils.clearConsoleOut();
        System.out.println("""
                        ______________
                       /|            |
                  ____/_|_____________|____
                 |                      O  |
                 '-(@)----------------(@)--'
                ===============================
                어떤 차량 타입을 선택할까요?
                1. Sedan
                2. SUV
                3. Truck
                ===============================
                """);
    }

    @Override
    public boolean isValidRange() {
        if (answer < 1 || answer > 3) {
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car) {
        CarType part = CarType.from(answer);
        car.setType(part);
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", part.name());
    }
}
