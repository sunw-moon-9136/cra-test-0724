package mission2.part;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum CarType {

    Sedan(1),
    SUV(2),
    Truck(3);

    @Getter
    private final int index;

    public static CarType from(int answer) {
        return Arrays.stream(CarType.values())
                .filter(type -> type.getIndex() == answer)
                .findAny().get();
    }
}