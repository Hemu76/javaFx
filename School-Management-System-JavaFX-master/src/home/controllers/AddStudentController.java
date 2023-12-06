package home.controllers;

import java.sql.SQLException;

import home.dao.DataDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField standardTextField;

	@FXML
	private TextField sectionTextField;

	@FXML
	private TextField rollNumberTextField;

	@FXML
	private Label messageLabel;

	@FXML
	public void saveStudent() throws SQLException {
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String studentClass = standardTextField.getText();
		String section = sectionTextField.getText();
		String rollNumber = rollNumberTextField.getText();

		System.out.println("Saving Student:");
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Class: " + studentClass);
		System.out.println("Section: " + section);
		System.out.println("RollNumber: " + rollNumber);

		if (firstName == "" || lastName == "" || studentClass == "" || section == "" || rollNumber == "") {
			messageLabel.setText("All Fields are mandatory!!");
		} else {

			int count = new DataDAO().insertStudent(firstName, lastName, studentClass, section, rollNumber);

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
