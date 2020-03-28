package DuckHunt.GameObjects.Ducks;

import java.awt.*;

public class RedDuck extends Duck {

    public RedDuck() {
        super(0, 7.0, 6 * 1000, 80, 30 * 1000, "On an island occupied by the cheeriest birds imaginable, Red is a bit of an outcast. His cynical outlook has kept him at odds with the other birds,  but a bit of cynicism might be just what they n" + "eed", "Images/Images/Menu_1.png", "The Red", 10, 0,new int[]{0,1,1}, Color.RED);
    }

    @Override
    public void bossMode() {
        setter(400,50,20,150,200,35*1000,10,true);
    }

}
