package DuckHunt.Online;

import DuckHunt.Constant.MessageType;
import DuckHunt.GUI.Base;
import DuckHunt.GameObjects.Ducks.BasicDuck;
import DuckHunt.GameObjects.Ducks.NewDuck;
import DuckHunt.GameObjects.Guns.Sniper;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Listeners.ListenGame;
import DuckHunt.Request.*;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class OnlineGame2 extends Group {
	
	private Group gameGroup;                //  Contains the game pannel and ducks
	private Text generalText;               //  A general text field for showing in game events
	private JFXTextField message;           //  The message text field
	private Player[] player;                //  List of player container
	private int roundNumber;                //  Current round number
	private NewDuck[] ducks;                //  Ducks list
	private Thread listen;
	private int opponentIndex;
	private boolean makeRountStart;
	private WebCamService service;
	private SocketCamService socketCamService;

//	public void init(){
//
//	}
	
	public OnlineGame2() {
		
		
		
		
		makeRountStart=false;
		GameGlobalVariables.getInstance().setActiveGun(new Sniper());
		GameGlobalVariables.getInstance().setOnline(true);
		roundNumber = 0;
		
		//  Screen Configuration
		
//		setHgap(0);
//		setVgap(0);
//		setPrefSize(1920,1080);
//		getColumnConstraints().addAll(new ColumnConstraints(1500),new ColumnConstraints(420));
//		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(980));

		//  Top Row
		
		//  1st column fill latter with stuff like group name, round number and so
		
		GridPane container = new GridPane();
		
		container.setPrefWidth(1500);
		container.setPrefHeight(100);
		container.setLayoutX(0);
		container.setLayoutY(0);
		
		container.getColumnConstraints().addAll(new ColumnConstraints(900),new ColumnConstraints(400),new ColumnConstraints(200));
		container.getRowConstraints().addAll(new RowConstraints(100));
		
		generalText = textCreater("");
		container.setHalignment(generalText, HPos.CENTER);
		message = new JFXTextField();
		message.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		container.addRow(0,generalText,message,buttonCreater("Send"));
		
		getChildren().add(container);
		
		// 2nd column some buttons
		
		HBox buttonContainer = new HBox(20);
		
		buttonContainer.setPrefWidth(420);
		buttonContainer.setPrefHeight(100);
		buttonContainer.setLayoutX(1500);
		buttonContainer.setLayoutY(0);
		
		buttonContainer.setPrefHeight(100);
		buttonContainer.setPrefWidth(420);
		buttonContainer.getChildren().addAll(buttonCreater("Provoke"),buttonCreater("Leave"));
		
		getChildren().add(buttonContainer);
		
		// Main game
		
		gameGroup = new Group();
		
		gameGroup.setLayoutX(0);
		gameGroup.setLayoutY(100);
		
		Image i1 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/level1.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView background = new ImageView(i1);
		background.setFitHeight(980);
		background.setFitWidth(1500);
		
		gameGroup.getChildren().add(background);
		
		getChildren().add(gameGroup);
		
		// Player Score Board kind of
		
		VBox playerContainer = new VBox();
		
		playerContainer.setPrefWidth(420);
		playerContainer.setPrefHeight(980);
		playerContainer.setLayoutX(1500);
		playerContainer.setLayoutY(100);
		
		playerContainer.setPrefHeight(980);
		playerContainer.setPrefWidth(420);
		player = new Player[2];
		player[0] = new Player();
		player[1] = new Player();
		playerContainer.getChildren().addAll(player[0],player[1]);
		
		getChildren().add(playerContainer);
		
		init();
		
		listen = new Thread(new ListenGame(this));
		listen.start();
 	}
	
	public void lostPlayer(String name) {
	
	}

	private void init() {
		setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),this);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupList(GameGlobalVariables.getInstance().getGamer().getGroupName()));
		GroupList groupList = (GroupList) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		String[] players = groupList.getClients();
		player[0].setName(players[0]);
		player[1].setName(players[1]);
		
		GameGlobalVariables.getInstance().getGamer().sendMessage(new OpponentAddress(null,0));
		OpponentAddress oppadd = ( (OpponentAddress) (GameGlobalVariables.getInstance().getGamer().receiveMessage()));
		Webcam cam = Webcam.getWebcams().get(0);
		service = new WebCamService(cam,oppadd);
		socketCamService = new SocketCamService();
		
		if (players[0].equals(GameGlobalVariables.getInstance().getGamer().getName())){
			player[0].setPlayer(true);
			player[0].setWebCamService(service);
			player[1].setWebCamService(socketCamService);
			opponentIndex = 1;
		}else{
			player[0].setWebCamService(socketCamService);
			player[1].setPlayer(true);
			player[1].setWebCamService(service);
			opponentIndex = 0;
		}
	}
	
	public JFXButton buttonCreater(String str){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(200);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				clickEventHandller(((JFXButton)e.getTarget()).getText());
			}
		});
		
		return button;
	}
	
	public Text textCreater(String str){
		Text text = new Text(str);
		text.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		return text;
	}
	
	public void clickEventHandller(String str) {
		System.out.println(str);
		if (str.equals("Send")) {
			sendAction();
		}else if (str.equals("Provoke")) {
			provokeAction();
		}else if (str.equals("Leave")) {
			leaveAction();
		}
	}
	
	private void provokeAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new Message(GameGlobalVariables.getInstance().getGamer().getName(),GameGlobalVariables.getInstance().getGamer().getGroupName(),message.getText(), MessageType.Provoke));
	}
	
	private void leaveAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new RemoveMember(GameGlobalVariables.getInstance().getGamer().getName()));
		GameGlobalVariables.getInstance().destroyGamer();
		Scene scene = this.getScene();
		scene.setRoot(new Base());
	}
	
	private void sendAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new Message(GameGlobalVariables.getInstance().getGamer().getName(),GameGlobalVariables.getInstance().getGamer().getGroupName(),message.getText(), MessageType.UserToGroup));
	}
	
	
	public void roundStart(GameState gameState) {
		roundNumber++;
		generalText.setText("Round " + roundNumber + " beginning.");
//		Date date = new Date();
//		while( (new Date().getTime() - date.getTime()) < 3000);
		if (ducks!=null)
		for (NewDuck d : ducks){
			if (d!=null)
			gameGroup.getChildren().remove(d.getImageView());
		}
		int numberOfDucks = roundNumber / 2 + 2;
		int numOfTrips = 20;
		ducks = new BasicDuck[numberOfDucks];
		for (int i = 0; i < numberOfDucks; i++) {
			ducks[i] = new BasicDuck(gameState.getX(i), gameState.getY(i), numOfTrips, false, roundNumber,i);
			ducks[i].play();
			gameGroup.getChildren().add(ducks[i].getImageView());
		}
		
	}
	
	public void gotMessage(String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				generalText.setText(s);
			}
		});
	}
	
	public void gameOver(GameOver obj) {
		System.out.println("Game Over");
		System.out.println(obj.getName());
		System.out.println(obj.getMessage());
	}
	
	public void provoke() {
		System.out.println("You have been provoked by your opponent.");
		player[opponentIndex].provoke();
	}
	
	public void makeMove(Move obj) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ducks[obj.getDuckNumber()].shotext(obj.getDamage());
			}
		});
	}
	
	public void updateScore(ScoreBoard obj) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				int[] score = obj.getScore();
				for (int i = 0; i < 2; i++) {
					player[i].setScore(score[i]);
					System.out.println(score[i]);
				}
			}
		});
		
	}
	
	public void newRound(GameState gameState) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				roundStart(gameState);
			}
		});
	}


//	public void checkOver() {
//		int tmp=0;
//		for (int i = 0; i < numOfDucks; i++) {
//			if (ducks[i].isDead()){
//				tmp++;
//			}
//		}
//		if (tmp==numOfDucks){
//			System.out.println("Round Over");
//			roundNumber++;
//			isRoundOver = true;
//			roundStart();
//		}
//		if(gameOverCheck()){
//			return;
//		}
//		update();
//	}
//
//	public void update() {
//		score.setText(""+GameGlobalVariables.getInstance().getScore());
//		roundNum.setText(""+roundNumber);
//		missed.setText(""+(missedAllowed - GameGlobalVariables.getInstance().getMissed()));
//	}
//
//
//	public Group getGroup() {
//		return group;
//	}
//
//	public boolean gameOverCheck(){
//		if (GameGlobalVariables.getInstance().getMissed() >= missedAllowed){
//			gameOver();
//			return true;
//		}
//		return false;
//	}
//
//	public void gameOver(){
//		System.out.println("GameOver");
//	}
//
//	public boolean isRoundOver() {
//		return isRoundOver;
//	}
//
}
