package DuckHunt.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class menu {
	
	@FXML
	JFXButton offline;
	
	public void offlineAction(ActionEvent actionEvent) {
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("../FXML/test.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		offline.getParent()
		Group group = (Group)offline.getScene().getRoot();
		ObservableList<Node> observableList = group.getChildren();
		observableList.remove(offline.getParent());
		observableList.add(parent);
		System.out.println("hi");
	}
	
	public void onlineAction(ActionEvent actionEvent) {
	}
	
	public void settingsAction(ActionEvent actionEvent) {
	}
	
	public void creditsAction(ActionEvent actionEvent) {
	}
	
	public void shopAction(ActionEvent actionEvent) {
	}
	
	public void quitAction(ActionEvent actionEvent) {
		System.exit(0);
	}
}
