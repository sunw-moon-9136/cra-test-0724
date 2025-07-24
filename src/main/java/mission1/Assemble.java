package mission1;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Q = 0;
    private static final int Engine_Q = 1;
    private static final int BrakeSystem_Q = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int TOYOTA_E = 2, WIA_E = 3;
    private static final int MANDO_B = 1, CONTINENTAL_B = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, BROKEN_E = 4;

    private static int[] stack = new int[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int step = CarType_Q;

        while (true) {
            clearConsoleOut();
            showMenuBy(step);

            String input = getInputFrom(scanner);
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
            if (!isValidRange(step, answer)) {
                delay(800);
                continue;
            }

            if (isGoBack(answer)) {
                step = undoAndGetNextStep(step);
                continue;
            }

            step = doStepAndGetNextStep(step, answer);
        }

        scanner.close();
    }

    private static int undoAndGetNextStep(int step) {
        if (step == Run_Test) {
            step = CarType_Q;
        } else if (step > CarType_Q) {
            step--;
        }
        return step;
    }

    private static int doStepAndGetNextStep(int step, int answer) {
        switch (step) {
            case CarType_Q:
                selectCarType(answer);
                delay(800);
                return Engine_Q;
            case Engine_Q:
                selectEngine(answer);
                delay(800);
                return BrakeSystem_Q;
            case BrakeSystem_Q:
                selectBrakeSystem(answer);
                delay(800);
                return SteeringSystem_Q;
            case SteeringSystem_Q:
                selectSteeringSystem(answer);
                delay(800);
                return Run_Test;
            case Run_Test:
                if (answer == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (answer == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    testProducedCar();
                    delay(2000);
                }
        }
        return step;
    }

    private static boolean isGoBack(int answer) {
        return answer == 0;
    }

    private static boolean isExit(String input) {
        return input.equalsIgnoreCase("exit");
    }

    private static String getInputFrom(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static void showMenuBy(int step) {
        switch (step) {
            case CarType_Q -> showCarTypeMenu();
            case Engine_Q -> showEngineMenu();
            case BrakeSystem_Q -> showBrakeMenu();
            case SteeringSystem_Q -> showSteeringMenu();
            case Run_Test -> showRunTestMenu();
        }
    }

    private static void clearConsoleOut() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CarType_Q -> {
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case Engine_Q -> {
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
            }
            case BrakeSystem_Q -> {
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case SteeringSystem_Q -> {
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
            }
            case Run_Test -> {
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
            }
        }
        return true;
    }

    private static void selectCarType(int a) {
        stack[CarType_Q] = a;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", a == 1 ? "Sedan" : a == 2 ? "SUV" : "Truck");
    }

    private static void selectEngine(int a) {
        stack[Engine_Q] = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }

    private static void selectBrakeSystem(int a) {
        stack[BrakeSystem_Q] = a;
        String name = getBrakeName(a);
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }

    private static String getBrakeName(int select) {
        return select == 1 ? "MANDO" : select == 2 ? "CONTINENTAL" : "BOSCH";
    }

    private static void selectSteeringSystem(int a) {
        stack[SteeringSystem_Q] = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private static boolean isValidCheck() {
        if (isContinentalConstraint()) return false;
        if (isToyotaConstraint()) return false;
        if (isWiaConstraint()) return false;
        if (isMandoConstraint()) return false;
        return !isBoschConstraint();
    }

    private static boolean isContinentalConstraint() {
        return stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL_B;
    }

    private static boolean isToyotaConstraint() {
        return stack[CarType_Q] == SUV && stack[Engine_Q] == TOYOTA_E;
    }

    private static boolean isWiaConstraint() {
        return stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA_E;
    }

    private static boolean isMandoConstraint() {
        return stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO_B;
    }

    private static boolean isBoschConstraint() {
        return stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S;
    }

    private static void runProducedCar() {
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

    private static void printRunnableCarInfo() {
        System.out.printf("Car Type : %s\n", getCarName());
        System.out.printf("Engine   : %s\n", getEngineName());
        System.out.printf("Brake    : %s\n", getBrakeName(stack[BrakeSystem_Q]));
        System.out.printf("Steering : %s\n", getSteeringName(stack[SteeringSystem_Q]));
        System.out.println("자동차가 동작됩니다.");
    }

    private static String getEngineName() {
        String[] engineNames = {"GM", "TOYOTA", "WIA"};
        return engineNames[stack[Engine_Q] - 1];
    }

    private static String getCarName() {
        String[] carNames = {"Sedan", "SUV", "Truck"};
        return carNames[stack[CarType_Q] - 1];
    }

    private static String getSteeringName(int select) {
        return select == 1 ? "Bosch" : "Mobis";
    }

    private static boolean isBrokenEngine() {
        return stack[Engine_Q] == BROKEN_E;
    }

    private static void testProducedCar() {
        if (isContinentalConstraint()) printFailMessage("Sedan에는 Continental제동장치 사용 불가");
        else if (isToyotaConstraint()) printFailMessage("SUV에는 TOYOTA엔진 사용 불가");
        else if (isWiaConstraint()) printFailMessage("Truck에는 WIA엔진 사용 불가");
        else if (isMandoConstraint()) printFailMessage("Truck에는 Mando제동장치 사용 불가");
        else if (isBoschConstraint()) printFailMessage("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        else System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private static void printFailMessage(String message) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(message);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}