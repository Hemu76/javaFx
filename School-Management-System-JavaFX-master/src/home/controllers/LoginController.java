package home.controllers;

import home.utility.DatabaseUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label messageLabel;

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void loginButtonAction() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		try {
			connection = DatabaseUtil.getConnection();
			preparedStatement = connection.prepareStatement("SELECT role FROM users WHERE username=? AND password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String role = resultSet.getString("role");
				openDashboard(role);
			} else {
				messageLabel.setText("Invalid username or password");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	private void openDashboard(String role) {
		if (role.equals("admin")) {
			Controller ctrl = new Controller();
			ctrl.loadStage("/home/fxml/Home.fxml");
		}
	}

	private void closeResources() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
