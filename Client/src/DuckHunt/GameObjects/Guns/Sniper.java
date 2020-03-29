package DuckHunt.GameObjects.Guns;


import DuckHunt.GameObjects.Bullets.Basic;

public class Sniper extends Gun {

    public Sniper() {
        super(1000, 5000, 5, "assets/media/gunShot1.wav",new Basic(),1);
    }

}
