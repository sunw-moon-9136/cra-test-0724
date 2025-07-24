package mission2.step;

import mission2.car.Car;
import mission2.part.Steering;
import mission2.util.CommonUtils;

public class SteeringStep extends Step {
    private static final SteeringStep steeringStep = new SteeringStep();

    public static SteeringStep getInstance() {
        return steeringStep;
    }

    @Override
    public void initPart(Car car) {
        car.setSteering(null);
    }

    @Override
    public void printMenu() {
        clearConsoleOut();
        System.out.println("""
                어떤 조향장치를 선택할까요?
                0. 뒤로가기
                1. BOSCH
                2. MOBIS
                ===============================
                """);
    }

    @Override
    public boolean isValidRange(int answer) {
        if (answer < 0 || answer > 2) {
            System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car, int answer) {
        Steering part = Steering.from(answer);
        car.setSteering(part);
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", part.name());
        CommonUtils.delay(800);
    }
}
