package DuckHunt.GUI;

import DuckHunt.Main.Game;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Optional;

public class MainMenu extends VBox {
	
	public MainMenu(){
		this.setFillWidth(true);
		this.setWidth(500);
		this.setHeight(600);
		this.getChildren().addAll(buttonCreater("Offline"),buttonCreater("Online"),buttonCreater("Settings"),buttonCreater("Shop"),buttonCreater("Credits"),buttonCreater("Quit"));
	}
	
	public JFXButton buttonCreater(String str){
		JFXButton button = new JFXButton();
		button.setPrefHeight(100);
		button.setPrefWidth(500);
		button.setText(str);
		button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		button.setTextAlignment(TextAlignment.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				clickEventHandller(((JFXButton)e.getTarget()).getText());
			}
		});
		
		if(str.equals("Quit")){
			button.setCancelButton(true);
		}else if (str.equals("Offline")){
			button.setDefaultButton(true);
		}
		
		return button;
	}
	
	public void clickEventHandller(String str) {
		System.out.println(str);
		if (str.equals("Offline")) {
			Scene scene = this.getScene();
			scene.setRoot(new Game().getGroup());
		} else if (str.equals("Online")) {
		
		} else if (str.equals("Settings")) {
		
		} else if (str.equals("Shop")) {
		
		} else if (str.equals("Credits")) {
		
		} else if (str.equals("Quit")) {
			JFXAlert alert = new JFXAlert();
			alert.setTitle("Test");
			alert.setHeaderText("This is a test.");
			alert.setResizable(false);
			alert.setContentText("Select okay or cancel this alert.");
			Optional<ButtonType> result = alert.showAndWait();
			if (!result.isPresent()) {
				System.out.println("Nukk");
			} else if (result.get() == ButtonType.OK) {
				System.out.println("Ok clicked");
			} else if (result.get() == ButtonType.CANCEL) {
				System.out.println("Cancel clicked");
			}
		}
	}
}
