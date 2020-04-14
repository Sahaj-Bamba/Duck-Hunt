package DuckHunt.GUI;

import DuckHunt.Constant.MessageType;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Listeners.ListenGroup;
import DuckHunt.Main.Game;
import DuckHunt.Online.OnlineGame;
import DuckHunt.Online.OnlineGame2;
import DuckHunt.Request.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import javafx.util.Duration;

public class GroupView extends GridPane {
	
	private Text groupName;
	private Text player1;
	private Text player2;
	private Text chatArea;
	private JFXTextField message;
	
	private Thread listen;
	
	public GroupView(){
		
		setPrefSize(600,600);
	
//		setHgap(10);
		setVgap(20);
		
		groupName = textCreater("");
		player1 = textCreater("");
		player2 = textCreater("");
		chatArea = textCreater("");
	
		message = new JFXTextField();
		
		chatArea.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		getColumnConstraints().addAll(new ColumnConstraints(200),new ColumnConstraints(200),new ColumnConstraints(200));
		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100));
		
		add(groupName, 1, 0);
		add(buttonCreater("Start Game", GameGlobalVariables.getInstance().getGamer().isIsOwner()), 0, 1);
		add(buttonCreater("Leave Group", true), 2, 1);
		add(player1, 0, 2);
		add(player2, 0, 3);
		add(chatArea,0,4,3,1);
		add(message,0,5,2,1);
		add(buttonCreater("Send",true),2,5);
		
		init();
		objectsInit();
		
	}
	
	private void objectsInit() {
		groupName.setText(GameGlobalVariables.getInstance().getGamer().getGroupName());
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupList(groupName.getText()));
		GroupList groupList = (GroupList) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		
		String[] x = groupList.getClients();
		player1.setText(x[0]);
		player2.setText(x[1]);
		
		listen = new Thread(new ListenGroup(this));
		listen.start();
		
	}
	
	private void init() {
		setOpacity(0);
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),this);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		setLayoutX(1920/2 - 600/2);
		setLayoutY(1080/2 - 600/2);
		fadeTransition.play();
	}
	
	public JFXButton buttonCreater(String str,boolean visibility){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(200);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setVisible(visibility);
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
		if (str.equals("Start Game")) {
			startAction();
		} else if (str.equals("Leave Group")) {
			leaveAction();
		} else if (str.equals("Send")) {
			sendAction();
		}
	}
	
	private void sendAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new Message(GameGlobalVariables.getInstance().getGamer().getName(),GameGlobalVariables.getInstance().getGamer().getGroupName(),message.getText(), MessageType.UserToGroup));
		message.setText("");
	}
	
	private void leaveAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new RemoveMember(GameGlobalVariables.getInstance().getGamer().getName()));
		GameGlobalVariables.getInstance().destroyGamer();
		backToMenu();
	}
	
	private void backToMenu() {
		Group group = (Group)this.getScene().getRoot();
		ObservableList<Node> observableList = group.getChildren();
		observableList.remove(this);
		observableList.add(new MainMenu());
	}
	
	
	private void startAction() {
		GameGlobalVariables.getInstance().getGamer().sendMessage(new StartGame(GameGlobalVariables.getInstance().getSIZE()));
	}
	
	
	public void gotMessage(String s) {
		chatArea.setText(s);
	}
	
	public void gotPlayer(String name) {
		if(player1.getText().equals("")){
			player1.setText(name);
		}else if(player2.getText().equals("")){
			player2.setText(name);
		}
		chatArea.setText(name + " joined the room. ");
	}
	
	public void lostPlayer(String name) {
		if(player1.getText().equals(name)){
			player1.setText("");
		}else if(player2.getText().equals(name)){
			player2.setText("");
		}
		chatArea.setText(name + " left the room. ");
	}
	
	public void startGame(int size) {
		System.out.println("Start Game");
//		GameGlobalVariables.getInstance().getGamer().sendMessage(new MoveToStart());
		Scene scene = this.getScene();
		scene.setRoot(new OnlineGame2());
	}
	
}
