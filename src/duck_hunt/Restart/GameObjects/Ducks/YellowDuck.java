package duck_hunt.Restart.GameObjects.Ducks;

import java.awt.*;

public class YellowDuck extends Duck {

    public YellowDuck() {
        super(0, 10.0, 8 * 1000, 100, 40 * 1000, "Chuck is constantly running at 100 mph and that includes his mouth as well . His impulsive nature often gets him into trouble. Lukily he is fast enough to escape from the situation. Chuck says I am not fast I am just walking its you who is slow . ", "Images/Images/Menu_2.png", "Chuck", 10, 1, new int[]{2,1,0}, Color.YELLOW);
    }

    @Override
    public void bossMode() {
        setter(400,50,20,150,200,35*1000,10,true);
    }

}
