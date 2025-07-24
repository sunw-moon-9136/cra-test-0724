package mission2.entity.step;

import lombok.Setter;
import mission2.entity.car.Car;

@Setter
public abstract class Step {

    protected Step backStep;
    protected Step afterStep;

    public abstract void initPart(Car car);

    public abstract void printMenu();

    public abstract boolean isValidRange(int answer);

    public Step process(Car car, int answer) {
        if (isGoBack(answer)) {
            return backStep;
        }

        doProcess(car, answer);
        return afterStep;
    }

    private boolean isGoBack(int answer) {
        return answer == 0;
    }

    protected abstract void doProcess(Car car, int answer);
}
