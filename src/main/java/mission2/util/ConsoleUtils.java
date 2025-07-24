package mission2.util;

import com.google.common.annotations.VisibleForTesting;
import lombok.Setter;

import java.util.Scanner;

public class ConsoleUtils {
    @VisibleForTesting
    @Setter
    private static final ConsoleUtils CONSOLE_UTILS_INSTANCE = new ConsoleUtils();

    public static ConsoleUtils getInstance() {
        return CONSOLE_UTILS_INSTANCE;
    }

    private static final Scanner SCANNER = new Scanner(System.in);

    public String getInput() {
        System.out.print("INPUT > ");
        return SCANNER.nextLine().trim();
    }

    public void closeScanner() {
        SCANNER.close();
    }
}
