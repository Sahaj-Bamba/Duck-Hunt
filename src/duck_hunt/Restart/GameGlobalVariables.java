package duck_hunt.Restart;

public class GameGlobalVariables {

    private static GameGlobalVariables gameGlobalVariables = null;

    private int ducksCount;

    private GameGlobalVariables(){
        ducksCount=10;
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

}
