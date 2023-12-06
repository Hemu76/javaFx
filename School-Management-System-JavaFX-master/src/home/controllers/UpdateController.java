package home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import home.dao.DataDAO;
import home.model.StudentsModel;

public class UpdateController implements Initializable{

		@FXML
		private TableView<StudentsModel> tbData;
		@FXML
		public TableColumn<StudentsModel, Integer> studentId;
		@FXML
		public TableColumn<StudentsModel, String> firstName;
		@FXML
		public TableColumn<StudentsModel, String> lastName;
		@FXML
		public TableColumn<StudentsModel, String> standard;
		@FXML
		public TableColumn<StudentsModel, String> section;
		@FXML
		public TableColumn<StudentsModel, String> rollNumber;

		@FXML
		private Button addStudentButton; // Add this line for the button

		public UpdateController() {

		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
			firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			standard.setCellValueFactory(new PropertyValueFactory<>("standard"));
			section.setCellValueFactory(new PropertyValueFactory<>("section"));
			rollNumber.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));

			// Fetch data from the database and populate the TableView
			loadStudentsData();
			
			// Add event handler for the "Add Student" button
	        addStudentButton.setOnAction(e -> openAddStudentPage());
		}

		// Method to refresh the data
	    public void refreshData() {
	        loadStudentsData();
	    }
	    
	    @FXML
	    public void handleTableClick(MouseEvent event) {
	        if (event.getClickCount() == 1) { // Check for single click
	            StudentsModel selectedStudent = tbData.getSelectionModel().getSelectedItem();
	            if (selectedStudent != null) {
	                // Call a method to open the edit dialog with the selected student data
	                openEditStudentPage(selectedStudent);
	            }
	        }
	    }
	    public void openEditStudentPage(StudentsModel selectedStudent) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/fxml/EditStudent.fxml"));
	            Parent root = loader.load();
	            EditStudentController editStudentController = loader.getController();
	            
	            // Pass the selected student data to the EditStudentController
	            editStudentController.initData(selectedStudent);

	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Edit Student");

	            // To wait for the new stage to close before refreshing, use a Modality.WINDOW_MODAL stage
	            stage.initModality(Modality.WINDOW_MODAL);
	            stage.initOwner(tbData.getScene().getWindow());

	            stage.showAndWait();

	            // Refresh data after the "Edit Student" stage is closed
	            refreshData();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


		private void loadStudentsData() {
			ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList();

			try {
				ResultSet resultSet = new DataDAO().getStudentsData();

				while (resultSet.next()) {
					int id = resultSet.getInt("studentid");
					String first_name = resultSet.getString("firstname");
					String last_name = resultSet.getString("lastname");
					String standard = resultSet.getString("standard");
					String section = resultSet.getString("section");
					String rollnumber = resultSet.getString("rollnumber");

					studentsModels.add(new StudentsModel(id, first_name, last_name, standard, section, rollnumber));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			tbData.setItems(studentsModels);
		}

		// Method to open the "Add Student" page
		public void openAddStudentPage() {
			try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/fxml/AddStudent.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Add Student");

	            // To wait for the new stage to close before refreshing, use a Modality.WINDOW_MODAL stage
	            stage.initModality(Modality.WINDOW_MODAL);
	            stage.initOwner(tbData.getScene().getWindow());

	            stage.showAndWait();

	            // Refresh data after the "Add Student" stage is closed
	            refreshData();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}


