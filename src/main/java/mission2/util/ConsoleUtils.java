package mission2.util;

import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.Scanner;

public class ConsoleUtils {
    private static final ConsoleUtils consoleUtils = new ConsoleUtils();

    public static ConsoleUtils getInstance() {
        return consoleUtils;
    }

    @VisibleForTesting
    @Setter(AccessLevel.PACKAGE)
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        System.out.print("INPUT > ");
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }
}
