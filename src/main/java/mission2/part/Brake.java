package mission2.part;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Brake {
    MANDO(1),
    CONTINENTAL(2),
    BOSCH(3);

    @Getter
    private final int index;

    public static Brake from(int answer) {
        return Arrays.stream(Brake.values())
                .filter(type -> type.getIndex() == answer)
                .findAny().get();
    }
}