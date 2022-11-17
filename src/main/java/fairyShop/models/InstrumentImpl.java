package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument {

    private int power = 0;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        if (this.power - 10 < 0) {
            this.power = 0;
        } else {
            this.power -= 10;
        }
    }

    @Override
    public boolean isBroken() {
        return this.power == 0;
    }
}
