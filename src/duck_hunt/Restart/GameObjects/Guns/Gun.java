package duck_hunt.Restart.GameObjects.Guns;

import duck_hunt.Restart.GameObjects.Bullets.Bullet;
import duck_hunt.Restart.utilities.playwav;

import java.util.Date;

public abstract class Gun {

    private long shotDelay;                                                   //      frames
    private long reloadDelay;                                                  //      frames
    private int maxClipSize;                                             //      number of bullets
    private int currentClip;
    private String picLocation;
    private String menuPicLocation;
    private String soundLocation;
    private int previousShoot = 0;
    private Date prevShootDate;
    private Bullet bullet;
    private int type;

                //      Getters and setters

    public String getMenuPicLocation() {
        return menuPicLocation;
    }

    public long getReloadDelay() {
        return reloadDelay;
    }

    public String getPicLocation() {
        return picLocation;
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

    private void setPic(){
        this.menuPicLocation = "Images/Images/Guns/menu/"+(this.type+1)+".png" ;
        this.picLocation = "Images/Images/Guns/"+(this.type+1)+".png" ;

    }

    public Gun(long shotDelay, long reloadDelay, int maxClipSize, String soundLocation, Bullet bullet, int type) {
        this.shotDelay = shotDelay;
        this.reloadDelay = reloadDelay;
        this.maxClipSize = maxClipSize;
        this.soundLocation = soundLocation;
        this.bullet = bullet;
        this.type = type;
        setPic();
    }

}
