package DuckHunt.GameObjects.Ducks;

import java.awt.*;

public class BlackDuck extends Duck {

    public BlackDuck() {
        super(0, 12.0, 2, 2, 40 * 1000, "Generally Speaking Bomb is the Chillest Bird you will ever meet. However get him worked up and he has a tendency to go Off - explosively speaking. This can have its advantages, but Bomb struggles to control his power.", "Images/Images/Menu_4.png", "Bomb", 10, 3, new int[]{0,2,1}, Color.BLACK);
    }

    @Override
    public void bossMode() {
        setter(400,50,20,150,200,35*1000,10,true);
    }

}
