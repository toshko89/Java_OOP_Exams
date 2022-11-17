package football_main.entities.player;

public class Men extends BasePlayer {

    private static final double KG = 85.50;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }

    @Override
    public void stimulation() {
        super.setStrength(145);
    }
}
