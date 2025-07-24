package mission1;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CAR_TYPE_Q = 0;
    private static final int ENGINE_Q = 1;
    private static final int BRAKE_SYSTEM_Q = 2;
    private static final int STEERING_SYSTEM_Q = 3;
    private static final int RUN_TEST = 4;

    private static final int SEDAN_T = 1, SUV_T = 2, TRUCK_T = 3;
    private static final int GM_E = 1, TOYOTA_E = 2, WIA_E = 3, BROKEN_E = 4;
    private static final int MANDO_B = 1, CONTINENTAL_B = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS_S = 2;

    public static final String GM = "GM", TOYOTA = "TOYOTA", WIA = "WIA";
    public static final String MANDO = "MANDO", CONTINENTAL = "CONTINENTAL", BOSCH = "BOSCH", MOBIS = "MOBIS";
    public static final String SEDAN = "Sedan", SUV = "SUV", TRUCK = "Truck";

    private static final int[] inputStack = new int[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int step = CAR_TYPE_Q;

        while (true) {
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
                step = goBackAndGetNextStep(step);
                continue;
            }

            step = doStepAndGetNextStep(step, answer);
        }

        scanner.close();
    }

    private static void showMenuBy(int step) {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();

        switch (step) {
            case CAR_TYPE_Q -> showCarTypeMenu();
            case ENGINE_Q -> showEngineMenu();
            case BRAKE_SYSTEM_Q -> showBrakeMenu();
            case STEERING_SYSTEM_Q -> showSteeringMenu();
            case RUN_TEST -> showRunTestMenu();
        }
    }

    private static void showCarTypeMenu() {
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

    private static void showEngineMenu() {
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

    private static void showBrakeMenu() {
        System.out.println("""
                어떤 제동장치를 선택할까요?
                0. 뒤로가기
                1. MANDO
                2. CONTINENTAL
                3. BOSCH
                ===============================
                """);
    }

    private static void showSteeringMenu() {
        System.out.println("""
                어떤 조향장치를 선택할까요?
                0. 뒤로가기
                1. BOSCH
                2. MOBIS
                ===============================
                """);
    }

    private static void showRunTestMenu() {
        System.out.println("""
                멋진 차량이 완성되었습니다.
                어떤 동작을 할까요?
                0. 처음 화면으로 돌아가기
                1. RUN
                2. Test
                ===============================
                """);
    }

    private static String getInputFrom(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static boolean isExit(String input) {
        return input.equalsIgnoreCase("exit");
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CAR_TYPE_Q -> {
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case ENGINE_Q -> {
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
            }
            case BRAKE_SYSTEM_Q -> {
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case STEERING_SYSTEM_Q -> {
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
            }
            case RUN_TEST -> {
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isGoBack(int answer) {
        return answer == 0;
    }

    private static int goBackAndGetNextStep(int step) {
        return switch (step) {
            case RUN_TEST, CAR_TYPE_Q -> CAR_TYPE_Q;
            default -> step - 1;
        };
    }

    private static int doStepAndGetNextStep(int step, int answer) {
        switch (step) {
            case CAR_TYPE_Q -> selectCarType(answer);
            case ENGINE_Q -> selectEngine(answer);
            case BRAKE_SYSTEM_Q -> selectBrakeSystem(answer);
            case STEERING_SYSTEM_Q -> selectSteeringSystem(answer);
            case RUN_TEST -> {
                selectRunOrTest(answer);
                return RUN_TEST;
            }
            default -> throw new IllegalArgumentException("Not Supported Step");
        }
        delay(800);
        return step + 1;
    }

    private static void selectCarType(int select) {
        inputStack[CAR_TYPE_Q] = select;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", getCarTypeName(select));
    }

    private static void selectEngine(int select) {
        inputStack[ENGINE_Q] = select;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", getEngineName(select));
    }

    private static void selectBrakeSystem(int select) {
        inputStack[BRAKE_SYSTEM_Q] = select;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", getBrakeName(select));
    }

    private static void selectSteeringSystem(int select) {
        inputStack[STEERING_SYSTEM_Q] = select;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", getSteeringName(select));
    }

    private static void selectRunOrTest(int answer) {
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

    private static void testProducedCar() {
        if (isContinentalConstraint()) printFailMessage("Sedan에는 Continental제동장치 사용 불가");
        else if (isToyotaConstraint()) printFailMessage("SUV에는 TOYOTA엔진 사용 불가");
        else if (isWiaConstraint()) printFailMessage("Truck에는 WIA엔진 사용 불가");
        else if (isMandoConstraint()) printFailMessage("Truck에는 Mando제동장치 사용 불가");
        else if (isBoschConstraint()) printFailMessage("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        else System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private static boolean isValidCheck() {
        if (isContinentalConstraint()) return false;
        if (isToyotaConstraint()) return false;
        if (isWiaConstraint()) return false;
        if (isMandoConstraint()) return false;
        return !isBoschConstraint();
    }

    private static boolean isBrokenEngine() {
        return inputStack[ENGINE_Q] == BROKEN_E;
    }

    private static void printRunnableCarInfo() {
        System.out.printf("Car Type : %s\n", getCarTypeName(inputStack[CAR_TYPE_Q]));
        System.out.printf("Engine   : %s\n", getEngineName(inputStack[ENGINE_Q]));
        System.out.printf("Brake    : %s\n", getBrakeName(inputStack[BRAKE_SYSTEM_Q]));
        System.out.printf("Steering : %s\n", getSteeringName(inputStack[STEERING_SYSTEM_Q]));
        System.out.println("자동차가 동작됩니다.");
    }

    private static boolean isContinentalConstraint() {
        return inputStack[CAR_TYPE_Q] == SEDAN_T && inputStack[BRAKE_SYSTEM_Q] == CONTINENTAL_B;
    }

    private static boolean isToyotaConstraint() {
        return inputStack[CAR_TYPE_Q] == SUV_T && inputStack[ENGINE_Q] == TOYOTA_E;
    }

    private static boolean isWiaConstraint() {
        return inputStack[CAR_TYPE_Q] == TRUCK_T && inputStack[ENGINE_Q] == WIA_E;
    }

    private static boolean isMandoConstraint() {
        return inputStack[CAR_TYPE_Q] == TRUCK_T && inputStack[BRAKE_SYSTEM_Q] == MANDO_B;
    }

    private static boolean isBoschConstraint() {
        return inputStack[BRAKE_SYSTEM_Q] == BOSCH_B && inputStack[STEERING_SYSTEM_Q] != BOSCH_S;
    }

    private static void printFailMessage(String message) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(message);
    }

    private static String getCarTypeName(int select) {
        return switch (select) {
            case SEDAN_T -> SEDAN;
            case SUV_T -> SUV;
            case TRUCK_T -> TRUCK;
            default -> throw new IllegalArgumentException("Not Supported Select Number");
        };
    }

    private static String getEngineName(int select) {
        return switch (select) {
            case GM_E -> GM;
            case TOYOTA_E -> TOYOTA;
            case WIA_E -> WIA;
            case BROKEN_E -> "고장난 엔진";
            default -> throw new IllegalArgumentException("Not Supported Select Number");
        };
    }

    private static String getBrakeName(int select) {
        return switch (select) {
            case MANDO_B -> MANDO;
            case CONTINENTAL_B -> CONTINENTAL;
            case BOSCH_B -> BOSCH;
            default -> throw new IllegalArgumentException("Not Supported Select Number");
        };
    }

    private static String getSteeringName(int select) {
        return switch (select) {
            case BOSCH_S -> BOSCH;
            case MOBIS_S -> MOBIS;
            default -> throw new IllegalArgumentException("Not Supported Select Number");
        };
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}