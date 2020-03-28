package DuckHunt.GameObjects.Guns;


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
    
    private void pause(long reloadDelay) {
    }
}
