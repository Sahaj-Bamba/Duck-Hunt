package DuckHunt.GameObjects.Ducks;

public class BasicDuck extends NewDuck {
	
	
	public BasicDuck (double X[],double Y[],int numberOfTrips,boolean isBoss,int level){
		super("red",X,Y,numberOfTrips,400,1,isBoss,50000,100,level);
	}
	
	@Override
	public String toString() {
		return "";
	}
}
