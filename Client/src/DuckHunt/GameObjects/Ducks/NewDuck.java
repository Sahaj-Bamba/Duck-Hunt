package DuckHunt.GameObjects.Ducks;

import DuckHunt.Constant.MoveType;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Main.Game;
import DuckHunt.Request.Move;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public abstract class NewDuck {
	
	private Image image[];
	private ImageView imageView;
	private int number;                                 //      A number to recognise the duck useful in multi player game
	private int hitPoints;                              //      Health of the duck
	private boolean isBoss;                             //      True if the bird is a boss
	private double speed;                               //      pixel per sec
	private boolean isAlive;                            //      Tell if the Duck is Alive
	private long entryDate;                             //      Entry Date
	private long score;                                 //      Score Awarded
	private double speedIncrement;                      //      Increment in the speed based on level
	private int level;                                  //      Level of the bird
	
	private SequentialTransition sequentialTransition;
	private TranslateTransition endTransition;
	
	private int[] property = new int[3];
	private Color color;
	
//	private int randomability;                          //      frames
//	private int leavetime;                              //      frames

	private String bio;                                 //      About me of the Duck
	private String picLocation;                         //      Pic Location of the Duck
	private String menuPicLocation;                     //      Pic Location of the Duck
	private String name;                                //      Name of the Duck
	//  Duck properties stars count for almanac
	
	public NewDuck(String name,double[] X,double[] Y,int numberOfTrips,double speed,int hitpoints,boolean isBoss,long score, int speedIncrement,int level,int number ){
		this.number = number;
		this.entryDate = new Date().getTime();
		this.speedIncrement = speedIncrement;
		this.level = level;
		this.score = score;
		this.isBoss = isBoss;
		this.isAlive = true;
		this.name = name;
		this.hitPoints = hitpoints;
		this.speed = speed;
		this.image = new Image[hitpoints+1];
		for (int i = 0; i < hitpoints+1; i++) {
			try {
				image[i] = new Image(new FileInputStream("assets/image/duck/"+name+"/"+i+".png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		imageView = new ImageView(image[hitpoints]);
		imageView.setX(X[0]);
		imageView.setY(Y[0]);
		imageView.setFitHeight(GameGlobalVariables.getInstance().getObjectSize());
		imageView.setFitWidth(GameGlobalVariables.getInstance().getObjectSize());
		
		sequentialTransition = new SequentialTransition(this.imageView);
		TranslateTransition[] translateTransition = new TranslateTransition[numberOfTrips];
		RotateTransition[] rotateTransitions = new RotateTransition[numberOfTrips];
		imageView.setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(20));
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		sequentialTransition.getChildren().addAll(new PauseTransition(Duration.millis(3000)),fadeTransition);
		double distance,time,newAngle,rotateBy,tmp1,tmp2,tmp3,angle=0;
		for (int i = 0; i < numberOfTrips; i++) {
			distance = Math.sqrt(Math.pow(X[i+1]-X[i],2)+Math.pow(Y[i+1]-Y[i],2));
			time = (distance*1000)/speed;
			newAngle = Math.acos(Math.abs(X[i+1]-X[i])/distance)*(180.0/Math.PI);
			if (X[i+1]>=X[i]){
				if(Y[i+1]<=Y[i]){
						//  First Quadrant
					newAngle = newAngle;
				}else{
						// Forth Quadrant
					newAngle = 360 - newAngle;
				}
			}else{
				if(Y[i+1]<=Y[i]){
					//  Second Quadrant
					newAngle = 180 - newAngle;
				}else{
					// Third Quadrant
					newAngle = 180 + newAngle;
				}
			}

			rotateBy = angle - newAngle;
			
			rotateTransitions[i] = new RotateTransition(Duration.millis(100));
			rotateTransitions[i].setByAngle(rotateBy);
			
			translateTransition[i] = new TranslateTransition(Duration.millis(time));
			translateTransition[i].setByY(Y[i+1]-Y[i]);
			translateTransition[i].setByX(X[i+1]-X[i]);
			sequentialTransition.getChildren().addAll(rotateTransitions[i],translateTransition[i]);
		
			endTransition = new TranslateTransition(Duration.millis(1000000/speed),imageView);
			endTransition.setByY(-1*1000);
			endTransition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					isAlive = false;
				}
			});
			sequentialTransition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					isAlive = false;
					GameGlobalVariables.getInstance().updateMissed();
					if (GameGlobalVariables.getInstance().isOnline()){
						if (GameGlobalVariables.getInstance().getGamer().isIsOwner()){
							GameGlobalVariables.getInstance().getGamer().sendMessage(new Move(number, MoveType.Left));
						}
					}
				}
			});
			
			angle = newAngle;
			
		}
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				GameGlobalVariables.getInstance().updateScore(shot(GameGlobalVariables.getInstance().getActiveGun().shot()));
			}
		};
		//Adding event Filter
		imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public void play(){
		this.sequentialTransition.play();
	}
	
	private long shot(int damage){
		if (GameGlobalVariables.getInstance().isOnline()){
			GameGlobalVariables.getInstance().getGamer().sendMessage(new Move(number,damage,MoveType.Damage));
		}
		hitPoints -= damage;
		if (hitPoints<=0){
			hitPoints=0;
		}
		imageView.setImage(image[hitPoints]);
		if (hitPoints==0){
			sequentialTransition.stop();
			endTransition.play();
			Glow glow = new Glow();
			glow.setLevel(0.8);
			imageView.setEffect(glow);
			isAlive = false;
			score = score - ((new Date().getTime() ) - entryDate );
			if (score<0){
				score = 0;
			}
			return score;
		}
		return 0;
	}
	
	public long shotext(int damage){
		hitPoints -= damage;
		if (hitPoints<=0){
			hitPoints=0;
		}
		imageView.setImage(image[hitPoints]);
		if (hitPoints==0){
			sequentialTransition.stop();
			endTransition.play();
			Glow glow = new Glow();
			glow.setLevel(0.8);
			imageView.setEffect(glow);
			isAlive = false;
			score = score - ((new Date().getTime() ) - entryDate );
			if (score<0){
				score = 0;
			}
			return score;
		}
		return 0;
	}
	
	public long hit(double x, double y,int damage){
		if(imageView.contains(x,y)){
			return shot(damage);
		}
		return 0;
	}
	
	public boolean isDead(){
		return !isAlive;
	}
	
}
