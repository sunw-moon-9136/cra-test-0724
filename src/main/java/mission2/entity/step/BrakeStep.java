package mission2.entity.step;

import mission2.entity.car.Car;
import mission2.entity.part.Brake;
import mission2.util.CommonUtils;
import mission2.util.ConsoleUtils;

public class BrakeStep extends Step {
    public BrakeStep(Step backStep, Step nextStep) {
        super(backStep, nextStep);
    }

    private static BrakeStep brakeStep;

    public static BrakeStep getInstance() {
        if (brakeStep == null) {
            brakeStep = new BrakeStep(EngineStep.getInstance(), SteeringStep.getInstance());
        }
        return brakeStep;
    }

    @Override
    public void initPart(Car car) {
        car.setBrake(null);
    }

    @Override
    public void printMenu() {
        ConsoleUtils.clearConsoleOut();
        System.out.println("""
                어떤 제동장치를 선택할까요?
                0. 뒤로가기
                1. MANDO
                2. CONTINENTAL
                3. BOSCH
                ===============================
                """);
    }

    @Override
    public boolean isValidRange() {
        if (answer < 0 || answer > 3) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car) {
        Brake part = Brake.from(answer);
        car.setBrake(part);
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", part.name());
        CommonUtils.delay(800);
    }
}
