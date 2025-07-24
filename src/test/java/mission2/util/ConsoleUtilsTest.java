package mission2.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ConsoleUtilsTest {

    public static final String NOT_IMPORTANT_TEXT = "test-input";
    ConsoleUtils consoleUtils = ConsoleUtils.getInstance();

    @AfterEach
    void clear() {
        System.setIn(System.in);
    }

    @Test
    void getInputTest() {
        //given
        InputStream inputStream = new ByteArrayInputStream(NOT_IMPORTANT_TEXT.getBytes());
        System.setIn(inputStream);
        consoleUtils.setScanner(new Scanner(System.in));

        //when
        var result = consoleUtils.getInput();

        //then
        assertThat(result).isEqualTo(NOT_IMPORTANT_TEXT);
    }
}