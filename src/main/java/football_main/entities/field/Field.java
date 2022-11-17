package football_main.entities.field;

import football_main.entities.player.Player;
import football_main.entities.supplement.Supplement;

import java.util.Collection;

public interface Field {
    int sumEnergy();

    void addPlayer(Player player);

    void removePlayer(Player player);

    void addSupplement(Supplement supplement);

    void drag();

    String getInfo();

    Collection<Player> getPlayers();

    Collection<Supplement> getSupplements();

    String getName();
}
