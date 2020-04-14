package DuckHunt.Request;

import DuckHunt.Constant.Request;
import DuckHunt.Global.GameGlobalVariables;

import java.io.Serializable;

public class GameState implements Serializable {
	
	private double[][] X;
	private double[][] Y;
	
	public GameState(int roundNumber,int numOfDucks,int numOfTrips){
		X = new double[numOfDucks][numOfTrips+1];
		Y = new double[numOfDucks][numOfTrips+1];
		for (int i = 0; i < numOfDucks; i++) {
			for (int j = 0; j < numOfTrips; j++) {
				X[i][j] = Math.random()*(GameGlobalVariables.getInstance().getGameScreenWidth() - GameGlobalVariables.getInstance().getObjectSize());
				Y[i][j] = 100 + Math.random()*(GameGlobalVariables.getInstance().getGameScreenHeight() - GameGlobalVariables.getInstance().getObjectSize());
			}
			X[i][numOfTrips] = 1920 + Math.random()*(200);
			Y[i][numOfTrips] = 1080 + Math.random()*(200);
		}
	}
	
	public double[] getX(int num) {
		return X[num];
	}
	
	public double[] getY(int num) {
		return Y[num];
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.GAMESTATE);
	}
	
}
