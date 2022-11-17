package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Collection<Gun> guns = mainPlayer.getGunRepository().getModels();
        if (mainPlayer.isAlive()) {
            for (Gun gun : guns) {
                for (Player civilPlayer : civilPlayers) {
                    while (gun.getTotalBullets() != 0) {
                        int fire = gun.fire();
                        civilPlayer.takeLifePoints(fire);
                        if (!civilPlayer.isAlive()) {
                            break;
                        }
                    }
                }
            }
        }
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Collection<Gun> civilPlayerGuns = civilPlayer.getGunRepository().getModels();
                for (Gun civilPlayerGun : civilPlayerGuns) {
                    while (civilPlayerGun.getTotalBullets() != 0) {
                        int fire = civilPlayerGun.fire();
                        mainPlayer.takeLifePoints(fire);
                        if (!mainPlayer.isAlive()) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
