package DuckHunt.GameObjects.Guns;


import javafx.scene.media.MediaPlayer;

import java.util.Date;

public class ReloadThread extends Thread {
    private Gun gun;

    public ReloadThread(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void run() {
        System.out.println("Reloading");
        Date date = new Date();
        while( (new Date().getTime() - date.getTime()) < gun.getReloadDelay());
        gun.reload();
    }
    
    private void pause(long reloadDelay) {
    }
}
