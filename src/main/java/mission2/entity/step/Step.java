package mission2.entity.step;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mission2.entity.car.Car;
import mission2.util.CommonUtils;

@Getter
@RequiredArgsConstructor
public abstract class Step {

    protected final Step backStep;
    protected final Step afterStep;

    @Setter
    protected int answer = -1;

    public abstract void printMenu();

    public abstract boolean isValidRange();

    public Step process(Car car) {
        if (isGoBack(answer)) {
            return backStep;
        }

        doProcess(car);
        // runtest 외에는 아래 delay 있음
        CommonUtils.delay(800);
        return afterStep;
    }

    private boolean isGoBack(int answer) {
        return answer == 0;
    }

    protected abstract void doProcess(Car car);
}
