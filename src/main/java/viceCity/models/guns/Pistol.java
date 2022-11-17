package viceCity.models.guns;

public class Pistol extends BaseGun {

    private static final int BULLETSPERBARREL = 10;
    private static final int TOTALBULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() {
        int count = 0;
        int bullets = getBulletsPerBarrel() - 1;
        count++;
        if (bullets < 0) {
            int total = getTotalBullets() - 10;
            if (getTotalBullets() - 10 < 0) {
                setTotalBullets(0);
                setCanFire(false);
                return count;
            }
            bullets = 10;
            setCanFire(true);
            setTotalBullets(total);
        }
        setCanFire(true);
        setBulletsPerBarrel(bullets);
        return count;
    }
}
