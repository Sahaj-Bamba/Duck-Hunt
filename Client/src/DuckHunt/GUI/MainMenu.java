package DuckHunt.GUI;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

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
		return button;
	}
	
	public void clickEventHandller(String str){
		System.out.println(str);
	}
	
}
