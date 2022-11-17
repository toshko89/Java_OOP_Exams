package glacialExpedition_test.models.explorers;

import glacialExpedition.models.explorers.BaseExplorer;

public class GlacierExplorer extends BaseExplorer {
    private static final double INITIAL_ENERGY = 40;

    public GlacierExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

}
