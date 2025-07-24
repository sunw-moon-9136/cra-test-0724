package mission2.part;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Steering {
    BOSCH(1),
    MOBIS(2);

    @Getter
    private final int index;

    public static Steering from(int answer) {
        return Arrays.stream(Steering.values())
                .filter(type -> type.getIndex() == answer)
                .findAny().get();
    }
}