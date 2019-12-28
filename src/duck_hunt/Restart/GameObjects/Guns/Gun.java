package duck_hunt.Restart.GameObjects.Guns;

import duck_hunt.Restart.GameObjects.Bullets.Bullet;
import duck_hunt.Restart.utilities.playwav;

import java.util.Date;

abstract class Gun {

    private long shotDelay;                                                   //      frames
    private long reloadDelay;                                                  //      frames
    private int maxClipSize;                                             //      number of bullets
    private int currentClip;
    private String picLocation;
    private String soundLocation;
    private int previousShoot = 0;
    private Date prevShootDate;
    private Bullet bullet;

                //      Getters and setters

    public long getReloadDelay() {
        return reloadDelay;
    }

                //        Main Functions

    public void reload(){
        this.currentClip=this.maxClipSize;
    }

    public int shot(){
        if (currentClip!=0) {
            if (new Date().getTime() - prevShootDate.getTime() >= shotDelay) {
                currentClip--;
                if (currentClip==0){
                    reloadTriggered();
                }
                prevShootDate = new Date();
                new playwav(this.soundLocation).start();
                return bullet.getDamage();
            }
        }
        return 0;
    }

    public void reloadTriggered(){
        new ReloadThread(this).start();
    }

    public Gun(long shotDelay, long reloadDelay, int maxClipSize, String picLocation, String soundLocation, Bullet bullet) {
        this.shotDelay = shotDelay;
        this.reloadDelay = reloadDelay;
        this.maxClipSize = maxClipSize;
        this.picLocation = picLocation;
        this.soundLocation = soundLocation;
        this.bullet = bullet;
    }

}
