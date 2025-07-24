package mission2.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class CommonUtilsTest {

    @Test
    void delay_no_exception_test() {
        // when, then
        assertThatNoException().isThrownBy(() -> CommonUtils.delay(1));
    }
}