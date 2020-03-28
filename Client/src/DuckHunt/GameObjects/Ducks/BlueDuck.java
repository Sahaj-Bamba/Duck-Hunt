package DuckHunt.GameObjects.Ducks;

import java.awt.*;

public class BlueDuck extends Duck {

    public BlueDuck() {
        super(0, 5.0, 4 * 1000, 50, 25 * 1000, "Why am I blue people usually ask me and my answer to them is because I am so cool. I am not bragging I really am cool. I listen to none since I am better then them all. I keep moving and changing directions as much as I want. That means almost infinitely. ", "Images/Images/Menu_3.png", "Blues", 10, 2, new int[]{0,1,2}, Color.BLUE);
    }

    @Override
    public void bossMode() {
        setter(400,50,20,150,200,35*1000,10,true);
    }

}
