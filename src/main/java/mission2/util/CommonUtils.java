package mission2.util;

import lombok.SneakyThrows;

public class CommonUtils {

    @SneakyThrows
    public static void delay(int ms) {
        Thread.sleep(ms);
    }
}
