package mission2;

import mission2.entity.car.Car;
import mission2.entity.step.StepOrderInitializer;
import mission2.util.ConsoleUtils;
import org.apache.commons.lang3.StringUtils;

import static mission2.util.CommonUtils.delay;

public class Main {

    public static void main(String[] args) {
        var currentStep = StepOrderInitializer.getInstance().getFirstStep();
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

            int answer = Integer.parseInt(input);
            if (!currentStep.isValidRange(answer)) {
                delay(800);
                continue;
            }

            currentStep = currentStep.process(car, answer);
        }

        ConsoleUtils.closeScanner();
    }

    private static boolean isExit(String input) {
        return input.equalsIgnoreCase("exit");
    }
}