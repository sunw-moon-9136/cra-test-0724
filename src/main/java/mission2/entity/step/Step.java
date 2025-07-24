package mission2.entity.step;

import lombok.Setter;
import mission2.entity.car.Car;

public abstract class Step {

    protected Step backStep;
    protected Step afterStep;

    @Setter
    protected int answer = -1;

    public void setSteps(Step backStep, Step afterStep) {
        this.backStep = backStep;
        this.afterStep = afterStep;
    }

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
