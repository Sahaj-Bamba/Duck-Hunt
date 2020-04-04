package DuckHunt.Other;

/**
 *
 * @author Sahaj
 */

import DuckHunt.Main.Owner;

/**
 * Different Global variables used everywhere
 *
 */

public class GameGlobalVariables {
    
    /**
     * The main instance of the class
     */
    private static GameGlobalVariables gameGlobalVariables = null;
    
    /**
     * Server Configuration Variables
     *
     */
    private int port;
    
    /**
     * Game Control Variables
     *
     */
    private int SIZE = 8;
    private Owner GAMER;
    
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
        port = 5560;
        GAMER = new Owner();
    }
    
    public int getSIZE() {
        return SIZE;
    }
    
    public int getPort() {
        return port;
    }
    
    public Owner getGAMER() {
        return GAMER;
    }
    
}
