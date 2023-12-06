package home.controllers;

import home.dao.DataDAO;
import home.model.StudentsModel;
import home.utility.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

	@FXML
	private Label totalStudentsLabel;
	@FXML
	private Label totalHoursLabel;
	@FXML
	private Label totalLessonsLabel;
	@FXML
	private Label missedClassesLabel;

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
	private PieChart pieChart;

	private ResultSet resultSet = null;
	
	private DataDAO data  = new DataDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			try {
				loadPieChart();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			loadStudentsData();
			loadDashboardData();
	}

	private void loadDashboardData() {
		try {
			// Get total students
			resultSet = data.getStudentsCount();
			if (resultSet.next()) {
				int totalStudents = resultSet.getInt("total_students");
				totalStudentsLabel.setText(String.valueOf(totalStudents));
			}

			// Get total hours, total lessons, and missed classes
			resultSet = data.getDashboardDetails();

			if (resultSet.next()) {
				int totalHours = resultSet.getInt("total_hours");
				int totalLessons = resultSet.getInt("total_lessons");
				int missedClasses = resultSet.getInt("missed_classes");

				totalHoursLabel.setText(String.valueOf(totalHours));
				totalLessonsLabel.setText(String.valueOf(totalLessons));
				missedClassesLabel.setText(String.valueOf(missedClasses));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadPieChart() throws SQLException {
		resultSet = data.getClassAttendanceData();

		while (resultSet.next()) {
			PieChart.Data slice1 = new PieChart.Data("Classes", resultSet.getInt("no_of_classes"));
			PieChart.Data slice2 = new PieChart.Data("Attendance", resultSet.getInt("attendance"));
			PieChart.Data slice3 = new PieChart.Data("Teachers", resultSet.getInt("no_of_teachers"));

			pieChart.getData().addAll(slice1, slice2, slice3);
		}
	}

	private void loadStudentsData() {
		try {
			ResultSet resultSet = data.getStudentsData();

			ObservableList<StudentsModel> studentsList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				int studentId = resultSet.getInt("StudentId");
				String firstName = resultSet.getString("FirstName");
				String lastName = resultSet.getString("LastName");
				String standard = resultSet.getString("standard");
				String section = resultSet.getString("section");
				String rollnumber = resultSet.getString("rollnumber");
				
				studentsList.add(new StudentsModel(studentId, firstName, lastName, standard, section, rollnumber));
			}

			studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
			firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
			standard.setCellValueFactory(new PropertyValueFactory<>("Standard"));
			section.setCellValueFactory(new PropertyValueFactory<>("Section"));
			rollNumber.setCellValueFactory(new PropertyValueFactory<>("RollNumber"));
			
			tbData.setItems(studentsList);

			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
