package mission2.entity.step;

import mission2.util.StepOrderInitializer;
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