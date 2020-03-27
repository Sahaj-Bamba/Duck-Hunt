package duck_hunt.Restart.GameObjects.Guns;

import static acm.util.JTFTools.pause;

public class ReloadThread extends Thread {
    private Gun gun;

    public ReloadThread(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void run() {
        pause(gun.getReloadDelay());
        gun.reload();
    }
}
