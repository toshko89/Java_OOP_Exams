package glacialExpedition_test.models.explorers;

import glacialExpedition_test.models.explorers.BaseExplorer;

public class NaturalExplorer extends BaseExplorer {
    private static final double INITIAL_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        setEnergy(Math.max(getEnergy() - 7, 0));
    }
}
