package mission2.entity.step;

import mission2.entity.car.Car;
import mission2.entity.part.Engine;
import mission2.util.CommonUtils;
import mission2.util.ConsoleUtils;

public class EngineStep extends Step {
    private static final EngineStep engineStep = new EngineStep();

    public static EngineStep getInstance() {
        return engineStep;
    }

    @Override
    public void initPart(Car car) {
        car.setEngine(null);
    }

    @Override
    public void printMenu() {
        ConsoleUtils.clearConsoleOut();
        System.out.println("""
                어떤 엔진을 탑재할까요?
                0. 뒤로가기
                1. GM
                2. TOYOTA
                3. WIA
                4. 고장난 엔진
                ===============================
                """);
    }

    @Override
    public boolean isValidRange(int answer) {
        if (answer < 0 || answer > 4) {
            System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car, int answer) {
        Engine part = Engine.from(answer);
        car.setEngine(part);
        System.out.printf("%s 엔진을 선택하셨습니다.\n", part.name());
        CommonUtils.delay(800);
    }
}
