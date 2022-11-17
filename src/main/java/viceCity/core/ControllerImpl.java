package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private Player mainPlayer = new MainPlayer();
    private Collection<Player> players = new ArrayList<>();
    private Repository<Gun> guns = new GunRepository();

    @Override
    public String addPlayer(String name) {
        Player civil = new CivilPlayer(name);
        players.add(civil);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        switch (type) {
            case "Pistol":
                Gun pistol = new Pistol(name);
                guns.add(pistol);
                return String.format(GUN_ADDED, name, type);
            case "Rifle":
                Gun rifle = new Rifle(name);
                guns.add(rifle);
                return String.format(GUN_ADDED, name, type);
        }
        return String.format(GUN_TYPE_INVALID);
    }

    @Override
    public String addGunToPlayer(String name) {
        Player civilPlayer = players.stream().filter(player -> player.getName().equals(name))
                .findFirst().orElse(null);
        List<Gun> gunList = guns.getModels().stream().collect(Collectors.toList());
        Deque<Gun> gunsDequeue = new ArrayDeque<>(gunList);
        if (guns.getModels().size() == 0) {
            return String.format(GUN_QUEUE_IS_EMPTY);
        }
        Gun gun = gunsDequeue.poll();
        if ("Vercetti".equals(name)) {
            mainPlayer.getGunRepository().getModels().add(gun);
            guns.remove(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        }
        if (civilPlayer == null) {
            return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
        }

        civilPlayer.getGunRepository().getModels().add(gun);
        guns.remove(gun);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        GangNeighbourhood gangNeighbourhood = new GangNeighbourhood();
        gangNeighbourhood.action(this.mainPlayer, this.players);
        boolean isOK = true;
        for (Player player : players) {
            int lifePoints = player.getLifePoints();
            if (lifePoints != 50) {
                isOK = false;
                break;
            }
        }
        if (mainPlayer.getLifePoints() == 100 && isOK) {
            return String.format(FIGHT_HOT_HAPPENED);
        }

        long countAlive = players.stream().filter(Player::isAlive).count();
        long countDead = players.size() - countAlive;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, countDead))
                .append(System.lineSeparator())
                .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, countAlive));

        return stringBuilder.toString().trim();
    }
}
