package mission2;

import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.Setter;
import mission2.car.Car;
import mission2.step.Step;
import mission2.util.ConsoleUtils;
import mission2.util.StepOrderInitializer;
import org.apache.commons.lang3.StringUtils;

import static mission2.util.CommonUtils.delay;

public class Main {

    @VisibleForTesting
    @Setter(AccessLevel.PACKAGE)
    private static ConsoleUtils consoleUtils = ConsoleUtils.getInstance();

    @VisibleForTesting
    @Setter(AccessLevel.PACKAGE)
    private static Step currentStep = StepOrderInitializer.getInstance().getFirstStep();

    public static void main(String[] args) {
        Car car = new Car();

        while (true) {
            currentStep.printMenu();

            String input = consoleUtils.getInput();
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

        consoleUtils.closeScanner();
    }


    private static boolean isExit(String input) {
        return input.equalsIgnoreCase("exit");
    }
}