package mission2.entity.part;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrakeTest {

    @Test
    void from_test_with_1() {
        assertThat(Brake.from(1)).isEqualTo(Brake.MANDO);
    }

    @Test
    void from_test_with_2() {
        assertThat(Brake.from(2)).isEqualTo(Brake.CONTINENTAL);
    }

    @Test
    void from_test_with_3() {
        assertThat(Brake.from(3)).isEqualTo(Brake.BOSCH);
    }
}