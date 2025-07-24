package mission2.part;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Engine {
    GM(1),
    TOYOTA(2),
    WIA(3),
    BROKEN(4);

    @Getter
    private final int index;

    public static Engine from(int answer) {
        return Arrays.stream(Engine.values())
                .filter(type -> type.getIndex() == answer)
                .findAny().get();
    }
}