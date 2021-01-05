package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements Initializable {
	
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXCheckBox checkbox;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton forgot;
    
    private DBHandler handler;
    private Connection connection;
    private java.sql.PreparedStatement pst;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		handler = new DBHandler();
	}
	
	@FXML
	public void loginAction(ActionEvent e) {
		connection = handler.getConnection();
		String q1 = "SELECT * from youtubers where names=? and password=?";
		
		try {
			pst = connection.prepareStatement(q1);
			pst.setString(1, username.getText());
			pst.setString(2, password.getText());
			ResultSet rs= pst.executeQuery();
			
			int count= 0;
			
			while(rs.next()){
				count++;
			}
			
			if(count==1) {
				login.getScene().getWindow().hide();
				
				Stage home = new Stage();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
					Scene scene = new Scene(root);
					home.setScene(scene);
					home.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				Alert alert= new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Username and Password not correct");
				alert.show();
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@FXML
	public void signUp(ActionEvent e1) throws IOException {
		login.getScene().getWindow().hide();
		
		Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);
		
	}
}
