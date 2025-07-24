package mission2.step;

import mission2.car.Car;
import mission2.part.Brake;
import mission2.util.CommonUtils;

public class BrakeStep extends Step {
    private static final BrakeStep brakeStep = new BrakeStep();

    public static BrakeStep getInstance() {
        return brakeStep;
    }

    @Override
    public void initPart(Car car) {
        car.setBrake(null);
    }

    @Override
    public void printMenu() {
        clearConsoleOut();
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
    public boolean isValidRange(int answer) {
        if (answer < 0 || answer > 3) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car, int answer) {
        Brake part = Brake.from(answer);
        car.setBrake(part);
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", part.name());
        CommonUtils.delay(800);
    }
}
