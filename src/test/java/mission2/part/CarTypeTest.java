package mission2.part;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTypeTest {

    @Test
    void from_when_invalid_answer() {
        assertThatThrownBy(() -> CarType.from(10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}