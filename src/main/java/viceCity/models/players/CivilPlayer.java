package viceCity.models.players;

public class CivilPlayer extends BasePlayer {

    private static final int LIFE = 50;

    public CivilPlayer(String name) {
        super(name, LIFE);
    }
}
