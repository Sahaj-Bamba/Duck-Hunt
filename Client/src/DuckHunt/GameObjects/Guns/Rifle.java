package DuckHunt.GameObjects.Guns;


import DuckHunt.GameObjects.Bullets.Basic;

public class Rifle extends Gun {
    public Rifle() {
        super(0, 1000, 1000,"assets/media/gunShot1.wav", new Basic(),0);
    }
}
