package mission2.step;

import mission2.car.Car;

import static mission2.util.CommonUtils.delay;

public class RunTestStep extends Step {
    private static final RunTestStep runTestStep = new RunTestStep();

    public static RunTestStep getInstance() {
        return runTestStep;
    }

    @Override
    public void initPart(Car car) {
        // TODO: Part 관련된부분은 나중에 interface로 변경
    }

    @Override
    public void printMenu() {
        clearConsoleOut();
        System.out.println("""
                멋진 차량이 완성되었습니다.
                어떤 동작을 할까요?
                0. 처음 화면으로 돌아가기
                1. RUN
                2. Test
                ===============================
                """);
    }

    @Override
    public boolean isValidRange(int answer) {
        if (answer < 0 || answer > 2) {
            System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
            return false;
        }
        return true;
    }

    @Override
    protected void doProcess(Car car, int answer) {
        if (answer == 1) {
            car.runProducedCar();
        } else if (answer == 2) {
            System.out.println("Test...");
            delay(1500);
            car.testProducedCar();
        }
        delay(2000);
    }
}
