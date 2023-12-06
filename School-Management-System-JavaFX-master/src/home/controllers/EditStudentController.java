package home.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import home.dao.DataDAO;
import home.model.StudentsModel;

public class EditStudentController {

    @FXML
    private TextField firstNameTextField; // Add all necessary fields for editing
    
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

    private StudentsModel selectedStudent;

    public void initData(StudentsModel selectedStudent) {
        this.selectedStudent = selectedStudent;
        // Initialize fields with the selected student data
        firstNameTextField.setText(selectedStudent.getFirstName());
        lastNameTextField.setText(selectedStudent.getLastName());
        standardTextField.setText(selectedStudent.getStandard());
        sectionTextField.setText(selectedStudent.getSection());
        rollNumberTextField.setText(selectedStudent.getRollNumber());
    }

    // Add methods to handle save and cancel actions
    @FXML
    private void handleSave() throws SQLException {
        int id = selectedStudent.getStudentId();
        String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String studentClass = standardTextField.getText();
		String section = sectionTextField.getText();
		String rollNumber = rollNumberTextField.getText();

        // Save the changes to the database or perform necessary actions
        if (firstName == "" || lastName == "" || studentClass == "" || section == "" || rollNumber == "") {
			messageLabel.setText("All Fields are mandatory!!");
		} else {

			int count = new DataDAO().updateStudent(firstName, lastName, studentClass, section, rollNumber, id);

			if (count == 1) {
				closeDialog();
			} else {
				System.out.println("Row Insertion Failed!!");
			}
		}
    }

    @FXML
    private void handleCancel() {
        // Close the edit dialog without saving changes
        closeDialog();
    }

    private void closeDialog() {
        // Get the reference to the stage and close it
        Stage stage = (Stage) firstNameTextField.getScene().getWindow();
        stage.close();
    }
    
    public void saveStudent() throws SQLException {
    	handleSave();
    }
    
    public void cancelOperation(){
    	handleCancel();
    }
}
