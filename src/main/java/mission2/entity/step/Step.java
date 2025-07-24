package mission2.entity.step;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mission2.entity.car.Car;

@Getter
@RequiredArgsConstructor
public abstract class Step {

    protected final Step backStep;
    protected final Step afterStep;

    @Setter
    protected int answer = -1;

    public abstract void initPart(Car car);

    public abstract void printMenu();

    public abstract boolean isValidRange();

    public Step process(Car car) {
        if (isGoBack(answer)) {
            return backStep;
        }

        doProcess(car);
        return afterStep;
    }

    private boolean isGoBack(int answer) {
        return answer == 0;
    }

    protected abstract void doProcess(Car car);
}
