package duck_hunt.Restart.GameObjects.Guns;

import duck_hunt.Restart.GameObjects.Bullets.Basic;

public class Rifle extends Gun {
    public Rifle() {
        super(200, 1000, 25, "Images/Images/Guns/1.jpg", "Images/Songs/1.wav", new Basic());
    }
}
