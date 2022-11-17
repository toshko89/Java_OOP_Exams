package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer {

    private static final double ENERGY_BASE = 40.00;

    public GlacierExplorer(String name) {
        super(name, ENERGY_BASE);
    }
}
