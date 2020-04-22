package DuckHunt.Global;

/**
 *
 * @author Sahaj
 */

import DuckHunt.GameObjects.Guns.Gun;
import DuckHunt.Main.Client;

import java.awt.*;

/**
 * Different Global variables used everywhere
 *
 */

public class
GameGlobalVariables {
    
    /**
     * The main instance of the class
     */
    private static GameGlobalVariables gameGlobalVariables = null;
    
    
    /**
     * Server Configuration Variables
     *
     */
    private int port;
    private String ip;
    private boolean isOnline;
    
    private Client gamer;
    
    /**
     * Screen size controls
     *
     */
    private double defaultScreenHeight;
    private double defaultScreenWidth;
    private double screenHeight;
    private double screenWidth;
    private double screenHeightFraction;
    private double screenWidthFraction;
    
    private double gameScreenWidth = 1920;
    private double gameScreenHeight = 980;
    private double objectSize = 100;
    private int numberOfTrips = 30;
    
    private Gun activeGun;
    private long score;
    private int missed;
    
    /**
     * Game Control Variables
     *
     */
    private int SIZE = 5;
    private int numberOfPlayers = 3;
    
    /**
     * Private constructor calls init method
     *
     */
    private GameGlobalVariables(){
        init();
    }
    
    /**
     * Creates the instance and return
     * @return the instance of the object
     */
    public static GameGlobalVariables getInstance(){
        if (gameGlobalVariables==null){
            gameGlobalVariables=new GameGlobalVariables();
        }
        return gameGlobalVariables;
    }
    
    /**
     * initialize different variables
     *
     */
    private void init(){
        calculateFraction(1080,1920);
        port = 5560;
        ip = "sahajbamba.me";
    }
    
    public int getSIZE() {
        return SIZE;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getip() {
        return ip;
    }
    
    public double getScreenHeightFraction() {
        return screenHeightFraction;
    }

    public double getScreenWidthFraction() {
        return screenWidthFraction;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    
    public Client getGamer() {
        if(gamer == null){
            gamerinit();
        }
        return gamer;
    }
    
    private void gamerinit() {
        gamer = new Client(ip,port);
    }
    
    public void destroyGamer() {
        gamer = null;
        isOnline = false;
    }
    
    /**
     * All The work of the screen size adjusting screen resolution independence
     *
     * @param x the width of the screen
     * @param y the height of the screen
     */
    public void calculateFraction(double x, double y){
    
        this.defaultScreenHeight=x;
        this.defaultScreenWidth=y;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeightFraction = screenHeight/defaultScreenHeight;
        screenWidthFraction = screenWidth/defaultScreenWidth;
    
    }
    
    public double getGameScreenWidth() {
        return gameScreenWidth;
    }
    
    public double getGameScreenHeight() {
        return gameScreenHeight;
    }
    
    public double getObjectSize() {
        return objectSize;
    }
    
    public int getNumberOfTrips() {
        return numberOfTrips;
    }
    
    public Gun getActiveGun() {
        return activeGun;
    }
    
    public void setActiveGun(Gun activeGun) {
        this.activeGun = activeGun;
    }
    
    public long getScore() {
        return score;
    }
    
    public void setScore(long score) {
        this.score = score;
    }
    
    public void updateScore(long points){
        score += points;
    }
    
    public int getMissed() {
        return missed;
    }
    
    public void setMissed(int missed) {
        this.missed = missed;
    }
    
    public void updateMissed(){
        this.missed++;
    }
    
    public boolean isOnline() {
        return isOnline;
    }
    
    public void setOnline(boolean online) {
        isOnline = online;
    }
}
