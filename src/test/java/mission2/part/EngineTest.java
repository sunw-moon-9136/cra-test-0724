package mission2.part;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EngineTest {

    @Test
    void from_when_invalid_answer() {
        assertThatThrownBy(() -> Engine.from(10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}