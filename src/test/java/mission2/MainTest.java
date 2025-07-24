package mission2;

import mission2.step.Step;
import mission2.util.ConsoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Spy
    ConsoleUtils consoleUtils;
    @Mock
    Step currentStep;

    @BeforeEach
    void setUp() {
        Main.setConsoleUtils(consoleUtils);
        Main.setCurrentStep(currentStep);
    }

    @Test
    void main_when_input_is_exit() {
        doReturn("exit").when(consoleUtils).getInput();

        Main.main(null);

        verify(currentStep, times(0)).isValidRange(anyInt());
    }

    @Test
    void main_when_input_is_not_number() {
        doReturn("not-number", "exit").when(consoleUtils).getInput();

        Main.main(null);

        verify(currentStep, times(0)).isValidRange(anyInt());
    }

    @Test
    void main_when_input_is_invalid_range() {
        doReturn("1", "exit").when(consoleUtils).getInput();
        doReturn(false).when(currentStep).isValidRange(anyInt());

        Main.main(null);

        verify(currentStep, times(1)).isValidRange(anyInt());
        verify(currentStep, times(0)).process(any(), anyInt());
    }

    @Test
    void main_when_input_is_valid_range() {
        doReturn("1", "exit").when(consoleUtils).getInput();
        doReturn(true).when(currentStep).isValidRange(anyInt());
        doReturn(currentStep).when(currentStep).process(any(), anyInt());

        Main.main(null);

        verify(currentStep, times(1)).isValidRange(anyInt());
        verify(currentStep, times(1)).process(any(), anyInt());
    }
}