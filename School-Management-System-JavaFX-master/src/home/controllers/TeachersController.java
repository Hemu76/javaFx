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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import home.dao.DataDAO;
import home.model.FacultyModel;

public class TeachersController implements Initializable {

	@FXML
	private TableView<FacultyModel> tbData;
	@FXML
	public TableColumn<FacultyModel, Integer> facultyId;
	@FXML
	public TableColumn<FacultyModel, String> facultyFirstName;
	@FXML
	public TableColumn<FacultyModel, String> facultyLastName;
	@FXML
	public TableColumn<FacultyModel, String> facultyQualification;
	
	@FXML
	private Button addFacultyButton; // Add this line for the button

	public TeachersController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facultyId.setCellValueFactory(new PropertyValueFactory<>("facultyId"));
		facultyFirstName.setCellValueFactory(new PropertyValueFactory<>("facultyFirstName"));
		facultyLastName.setCellValueFactory(new PropertyValueFactory<>("facultyLastName"));
		facultyQualification.setCellValueFactory(new PropertyValueFactory<>("facultyQualification"));
		
		// Fetch data from the database and populate the TableView
		loadFacultyData();
		
		// Add event handler for the "Add Faculty" button
		addFacultyButton.setOnAction(e -> openAddFacultyPage());
	}

	// Method to refresh the data
    public void refreshData() {
    	loadFacultyData();
    }

	private void loadFacultyData() {
		ObservableList<FacultyModel> facultyModels = FXCollections.observableArrayList();

		try {
			ResultSet resultSet = new DataDAO().getFacultysData();

			while (resultSet.next()) {
				int id = resultSet.getInt("facultyid");
				String first_name = resultSet.getString("facultyfirstname");
				String last_name = resultSet.getString("facultylastname");
				String qualification = resultSet.getString("facultyqualification");

				facultyModels.add(new FacultyModel(id, first_name, last_name, qualification));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		tbData.setItems(facultyModels);
	}

	// Method to open the "Add Faculty" page
	public void openAddFacultyPage() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/fxml/AddFaculty.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Faculty");

            // To wait for the new stage to close before refreshing, use a Modality.WINDOW_MODAL stage
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tbData.getScene().getWindow());

            stage.showAndWait();

            // Refresh data after the "Add Faculty" stage is closed
            refreshData();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
