package DuckHunt.GUI;

import DuckHunt.Constant.Request;
import DuckHunt.Constant.Responses;
import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Main.Game;
import DuckHunt.Request.GroupDetails;
import DuckHunt.Request.Response;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import javafx.util.Duration;

public class GroupSelect extends GridPane {
	
	private JFXTextField clientName;
	private JFXTextField groupName;
	private JFXPasswordField password;
	
	private Text error;
	
	public GroupSelect(){
		
		setPrefSize(600,600);
	
//		setHgap(10);
		setVgap(20);
		
		HBox buttonContainer = new HBox();
		buttonContainer.setPrefHeight(100);
		buttonContainer.setPrefWidth(100);
		
		buttonContainer.getChildren().addAll(buttonCreater("Create Group"),buttonCreater("Join Group"),buttonCreater("Random"));
		
		Text text1 = textCreater("Your Name");
		Text text2 = textCreater("Group Name");
		Text text3 = textCreater("Password");
		error = textCreater("");
		Font f = error.getFont();
		
		clientName = new JFXTextField();
		groupName = new JFXTextField();
		password = new JFXPasswordField();
		
		clientName.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		groupName.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		password.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 18));
		
		
		getColumnConstraints().addAll(new ColumnConstraints(300),new ColumnConstraints(300));
		getRowConstraints().addAll(new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100),new RowConstraints(100));
		
		add(text1, 0, 0);
		add(text2, 0, 1);
		add(text3, 0, 2);
		add(clientName, 1, 0);
		add(groupName, 1, 1);
		add(password, 1, 2);
		add(error,0,5,2,1);
		
		add(buttonContainer,0,3,2,2);
		
		init();
		
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
		if (str.equals("Create Group")) {
			createAction();
		} else if (str.equals("Join Group")) {
			joinAction();	
		} else if (str.equals("Random")) {
			randomAction();	
		}
	}
	
	private void randomAction() {
		
		if (clientName.getText().equals("" )) {
			error.setText("Please enter name.");
			return;
		}
		
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupDetails("", "", clientName.getText(), String.valueOf(Request.RANDOM)));
		
		Response response = (Response) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		error.setText(response.getErrorMessage());
		
		if(response.getStatus().equals(Responses.OK))
		{
			GameGlobalVariables.getInstance().getGamer().setName(clientName.getText(),response.getErrorMessage());
			nextStage();
		}else{
		
		}
		
	}
	
	private void joinAction() {
		
		if (groupName.getText()=="" || password.getText()=="" || clientName.getText()=="") {
			error.setText("Please enter name and Password.");
			return;
		}
		
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupDetails(password.getText(), groupName.getText(), clientName.getText(), String.valueOf(Request.JOINGROUP)));
		
		Response response = (Response) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		error.setText(response.getErrorMessage());
		
		if(response.getStatus().equals(Responses.OK))
		{
			GameGlobalVariables.getInstance().getGamer().setName(clientName.getText(),groupName.getText());
			nextStage();
		}else{
		
		}
		
	}
	
	private void createAction() {
		if (groupName.getText()=="" || password.getText()=="" || clientName.getText()=="") {
			error.setText("Please enter name and Password.");
			return;
		}
		
		GameGlobalVariables.getInstance().getGamer().sendMessage(new GroupDetails(password.getText(), groupName.getText(), clientName.getText(), String.valueOf(Request.CREATEGROUP)));
		
		Response response = (Response) GameGlobalVariables.getInstance().getGamer().receiveMessage();
		error.setText(response.getErrorMessage());
		
		if(response.getStatus().equals(Responses.OK))
		{
			GameGlobalVariables.getInstance().getGamer().setName(clientName.getText(),groupName.getText());
			GameGlobalVariables.getInstance().getGamer().makeOwner();
			nextStage();
			
		}else{
		
		}
	}
	
	private void nextStage() {
		System.out.println("Ready for Group Wait");
		Group group = (Group)this.getScene().getRoot();
		ObservableList<Node> observableList = group.getChildren();
		observableList.remove(this);
		observableList.add(new GroupView());
	}
	
	
}
