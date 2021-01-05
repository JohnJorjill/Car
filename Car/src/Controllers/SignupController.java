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
import javafx.stage.Stage;
import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController implements Initializable {
	
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton login;
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		handler = new DBHandler();
		
	}
	
	@FXML
	public void signUP(ActionEvent ae1) {
		String insert = "INSERT INTO youtubers(names,password,gender,location)" + "VALUES(?,?,?,?)";
		connection = handler.getConnection();
		try {
			pst = connection.prepareStatement(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pst.setString(1, username.getText());
			pst.setString(2, password.getText());
			pst.setString(3, getGender());
			pst.setString(4, location.getText());
			
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

	}
	
	@FXML
	public void loginAction(ActionEvent ae1) throws IOException {
		signup.getScene().getWindow().hide();
		
		Stage login = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root);
		login.setScene(scene);
		login.show();
		login.setResizable(false);
	}
	
	public String getGender() {
		String gen = "";
		
		if(male.isSelected()) {
			gen = "Male";
		}else if (female.isSelected()) {
			gen = "female";
		}
		
		return gen;
	}

}
