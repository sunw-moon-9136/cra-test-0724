package mission2;

import mission2.entity.car.Car;
import mission2.entity.step.*;
import mission2.util.ConsoleUtils;
import org.apache.commons.lang3.StringUtils;

import static mission2.util.CommonUtils.delay;

public class Main {

    public static void main(String[] args) {
        initSteps();

        Step currentStep = CarTypeStep.getInstance();
        Car car = new Car();

        while (true) {
            currentStep.initPart(car);
            currentStep.printMenu();
            String input = ConsoleUtils.getInputFrom();

            if (isExit(input)) {
                System.out.println("바이바이");
                break;
            }

            if (!StringUtils.isNumeric(input)) {
                System.out.println("ERROR :: 숫자만 입력 가능");
                delay(800);
                continue;
            }

            currentStep.setAnswer(Integer.parseInt(input));
            if (!currentStep.isValidRange()) {
                delay(800);
                continue;
            }

            currentStep = currentStep.process(car);
        }

        ConsoleUtils.closeScanner();
    }

    private static void initSteps() {
        CarTypeStep.getInstance().setSteps(CarTypeStep.getInstance(), EngineStep.getInstance());
        EngineStep.getInstance().setSteps(CarTypeStep.getInstance(), BrakeStep.getInstance());
        BrakeStep.getInstance().setSteps(EngineStep.getInstance(), SteeringStep.getInstance());
        SteeringStep.getInstance().setSteps(BrakeStep.getInstance(), RunTestStep.getInstance());
        RunTestStep.getInstance().setSteps(CarTypeStep.getInstance(), RunTestStep.getInstance());
    }

    private static boolean isExit(String input) {
        return input.equalsIgnoreCase("exit");
    }
}