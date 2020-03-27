package duck_hunt.Restart;

import java.awt.*;

public class GameGlobalVariables {

    private static GameGlobalVariables gameGlobalVariables = null;

    private int ducksCount;
    private double defaultScreenHeight;
    private double defaultScreenWidth;
    private double screenHeight;
    private double screenWidth;
    private double screenHeightFraction;
    private double screenWidthFraction;

    private GameGlobalVariables(){
        ducksCount=10;
        calculateFraction(1080,1920);
    }

    public static GameGlobalVariables getInstance(){
        if (gameGlobalVariables==null){
            gameGlobalVariables=new GameGlobalVariables();
        }
        return gameGlobalVariables;
    }

    public int getDucksCount() {
        return ducksCount;
    }

    public void setDucksCount(int ducksCount) {
        this.ducksCount = ducksCount;
    }

    public double getScreenHeightFraction() {
        return screenHeightFraction;
    }

    public double getScreenWidthFraction() {
        return screenWidthFraction;
    }

    public void calculateFraction(double x, double y){
        //All The work of the screen size adjusting screen resolution independence
        this.defaultScreenHeight=x;
        this.defaultScreenWidth=y;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeightFraction = screenHeight/defaultScreenHeight;
        screenWidthFraction = screenWidth/defaultScreenWidth;
    }

}
