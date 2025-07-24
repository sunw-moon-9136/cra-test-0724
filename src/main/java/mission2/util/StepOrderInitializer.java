package mission2.util;

import mission2.entity.step.*;

import java.util.List;

public class StepOrderInitializer {
    private static final StepOrderInitializer STEP_POINTER_INITIALIZER = new StepOrderInitializer();

    // Step 순서 관리
    List<Step> steps =
            List.of(CarTypeStep.getInstance(),
                    EngineStep.getInstance(),
                    BrakeStep.getInstance(),
                    SteeringStep.getInstance(),
                    RunTestStep.getInstance());

    private StepOrderInitializer() {
        initStepOrder();
    }

    public static StepOrderInitializer getInstance() {
        return STEP_POINTER_INITIALIZER;
    }

    public Step getFirstStep() {
        return steps.get(0);
    }

    private void initStepOrder() {
        for (int currentIndex = 0; currentIndex < steps.size(); currentIndex++) {
            steps.get(currentIndex).setBackStep(steps.get(getBackIndex(currentIndex)));
            steps.get(currentIndex).setAfterStep(steps.get(getAfterIndex(currentIndex)));
        }
        steps.get(steps.size() - 1).setBackStep(steps.get(0));
    }

    private static int getBackIndex(int currentIndex) {
        return currentIndex - 1 > 0 ? currentIndex - 1 : currentIndex;
    }

    private int getAfterIndex(int currentIndex) {
        return currentIndex + 1 < steps.size() ? currentIndex + 1 : currentIndex;
    }
}
