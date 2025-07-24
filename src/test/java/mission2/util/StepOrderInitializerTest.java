package mission2.util;

import mission2.entity.step.CarTypeStep;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StepOrderInitializerTest {

    StepOrderInitializer stepOrderInitializer = StepOrderInitializer.getInstance();

    @Test
    void getFirstStep() {
        // when
        var result = stepOrderInitializer.getFirstStep();

        // then
        assertThat(result).isEqualTo(CarTypeStep.getInstance());
    }
}