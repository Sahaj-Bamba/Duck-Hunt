package duck_hunt.Restart.GameObjects.Bullets;

public abstract class Bullet {

    private int Damage;                                                  //      integer

    public Bullet(int damage) {
        Damage = damage;
    }

    public int getDamage() {
        return Damage;
    }

}
