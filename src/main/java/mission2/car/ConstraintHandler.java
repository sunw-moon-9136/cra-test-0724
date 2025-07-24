package mission2.car;

public interface ConstraintHandler {

    boolean isInvalid(Car car);

    String getErrorMessage();
}
