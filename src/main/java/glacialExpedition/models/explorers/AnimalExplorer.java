package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer {

    private static final double ENERGY_BASE = 100.00;

    public AnimalExplorer(String name) {
        super(name, ENERGY_BASE);
    }
}
