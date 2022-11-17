package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {

    private static final double CUBICCENTIMETERS = 3000.00;

    public SportsCar(String model, int horsePower) {
        super(model, setHorsePower(horsePower), CUBICCENTIMETERS);
    }

    private static int setHorsePower(int horsePower) {
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }
}
