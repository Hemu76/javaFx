package home.controllers;

import java.sql.SQLException;

import home.dao.DataDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFacultyController {

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField qualificationTextField;

	@FXML
	private Label messageLabel;

	@FXML
	public void saveStudent() throws SQLException {
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String qualification = qualificationTextField.getText();

		System.out.println("Saving Faculty:");
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Qualification: " + qualification);
		
		if (firstName == "" || lastName == "" || qualification == "" ) {
			messageLabel.setText("All Fields are mandatory!!");
		} else {

			int count = new DataDAO().insertFaculty(firstName, lastName, qualification);

			if (count == 1) {
				// Close the add student window (you need to obtain the stage for this)
				Stage stage = (Stage) firstNameTextField.getScene().getWindow();
				stage.close();
			} else {
				System.out.println("Row Insertion Failed!!");
			}
		}
	}
}
