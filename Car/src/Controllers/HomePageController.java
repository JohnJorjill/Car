package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class HomePageController implements Initializable{
	
	@FXML AnchorPane holderPane;
	
	AnchorPane home;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createPage();
	}
	
	private void setNode(Node node) {
		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);
		
	}

	private void createPage() {
		try {
			home = FXMLLoader.load(getClass().getResource("/FXML/HOME.fxml"));
			setNode(home);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
