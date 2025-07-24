package mission2.step;

import lombok.Setter;
import mission2.car.Car;

@Setter
public abstract class Step {

    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    protected Step backStep;
    protected Step afterStep;

    public abstract void printMenu();

    public abstract boolean isValidRange(int answer);

    public Step process(Car car, int answer) {
        initPart(car);
        if (isGoBack(answer)) {
            return backStep;
        }

        doProcess(car, answer);
        return afterStep;
    }

    protected abstract void initPart(Car car);

    protected void clearConsoleOut() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private boolean isGoBack(int answer) {
        return answer == 0;
    }

    protected abstract void doProcess(Car car, int answer);
}
