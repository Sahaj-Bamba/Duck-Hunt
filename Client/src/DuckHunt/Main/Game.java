package DuckHunt.Main;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game {
	
	Group group;
	
	public Game(){
		group = new Group();
		ObservableList<Node> observableList = group.getChildren();
		
		Image i1 = null;
		Image i2 = null;
		try {
			i1 = new Image(new FileInputStream("assets/image/level1.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView background = new ImageView(i1);
		background.setX(0);
		
		background.setY(90);
		background.setFitHeight(900);
		background.setFitWidth(1500);
		observableList.add(background);
		
	}
	
	public Group getGroup() {
		return group;
	}
	
}
