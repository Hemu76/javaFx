package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnFaculty;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAddStudent;
    
    @FXML
    private Button btnLogout;


    //my bad - the freaking mouse event
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnDashboard) {
        	loadStage("/home/fxml/Dashboard.fxml");
        } else if (mouseEvent.getSource() == btnStudents) {
            loadStage("/home/fxml/Students.fxml");
        } else if (mouseEvent.getSource() == btn_Timetable) {
            loadStage("/home/fxml/Timetable.fxml");
        } else if (mouseEvent.getSource() == btnAddStudent) {
            loadStage("/home/fxml/AddStudent.fxml");
        } else if (mouseEvent.getSource() == btnFaculty) {
            loadStage("/home/fxml/Teachers.fxml");
        } else if (mouseEvent.getSource() == btnUpdate) {
            loadStage("/home/fxml/updateStudent.fxml");
        } 
    }
    
    @FXML
    private void handleLogout() {
        // Get the reference to the stage and close it
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
