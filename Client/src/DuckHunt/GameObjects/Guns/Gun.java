package DuckHunt.GameObjects.Guns;

import DuckHunt.GameObjects.Bullets.Bullet;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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
    private Media media;
    private Media reloadMedia;
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
            if (new Date().getTime() - prevShootDate.getTime() >= shotDelay || new Date().getTime() - prevShootDate.getTime() < 10) {
//                currentClip--;
                if (currentClip==0){
                    reloadTriggered();
                }
                prevShootDate = new Date();
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
        media = new Media(new File(soundLocation).toURI().toString());
        reloadMedia = new Media(new File("assets/media/reload.wav").toURI().toString());
        this.shotDelay = shotDelay;
        this.reloadDelay = reloadDelay;
        this.currentClip = this.maxClipSize = maxClipSize;
        this.soundLocation = soundLocation;
        this.bullet = bullet;
        this.type = type;
        setPic();
        prevShootDate = new Date();
    }
    
    public void fakeShot(){
        if (currentClip!=0) {
            if (new Date().getTime() - prevShootDate.getTime() >= shotDelay || new Date().getTime() - prevShootDate.getTime() < 10) {
                System.out.println(new Date().getTime() - prevShootDate.getTime());
                currentClip--;
                if (currentClip==0){
                    new MediaPlayer(reloadMedia).play();
                    reloadTriggered();
                }
                new MediaPlayer(media).play();
                prevShootDate = new Date();
            }
        }
    }
}
