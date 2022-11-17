package viceCity.models.guns;

public class Rifle extends BaseGun {

    private static final int BULLETSPERBARREL = 50;
    private static final int TOTALBULLETS = 500;

    public Rifle(String name) {
        super(name, BULLETSPERBARREL, TOTALBULLETS);
    }

    @Override
    public int fire() {
        int count = 0;
        int bullets = getBulletsPerBarrel() - 5;
        count += 5;
        if (bullets < 0) {
            int total = getTotalBullets() - 50;
            if (getTotalBullets() - 50 < 0) {
                setTotalBullets(0);
                return count;
            }
            bullets = 50;
            setTotalBullets(total);
        }
        setCanFire(true);
        setBulletsPerBarrel(bullets);
        return count;
    }
}
