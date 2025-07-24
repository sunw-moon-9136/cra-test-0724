package mission2.util;

import java.util.Scanner;

public class ConsoleUtils {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void clearConsoleOut() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public static String getInputFrom() {
        System.out.print("INPUT > ");
        return SCANNER.nextLine().trim();
    }

    public static void closeScanner() {
        SCANNER.close();
    }
}
