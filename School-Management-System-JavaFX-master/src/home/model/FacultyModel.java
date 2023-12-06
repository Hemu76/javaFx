package home.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FacultyModel {

    private SimpleIntegerProperty facultyId;
    private SimpleStringProperty facultyFirstName;
    private SimpleStringProperty facultyLastName;
    private SimpleStringProperty facultyQualification;

    public FacultyModel(Integer facultyId, String facultyFirstName, String facultyLastName, String facultyQualification) {
        this.facultyId = new SimpleIntegerProperty(facultyId);
        this.facultyFirstName = new SimpleStringProperty(facultyFirstName);
        this.facultyLastName = new SimpleStringProperty(facultyLastName);
        this.facultyQualification = new SimpleStringProperty(facultyQualification);
    }

    public int getFacultyId() {
        return facultyId.get();
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = new SimpleIntegerProperty(facultyId);
    }

    public String getFacultyFirstName() {
        return facultyFirstName.get();
    }

    public void setFacultyFirstName(String facultyFirstName) {
        this.facultyFirstName = new SimpleStringProperty(facultyFirstName);
    }

    public String getFacultyLastName() {
        return facultyLastName.get();
    }

    public void setFacultyLastName(String facultyLastName) {
        this.facultyLastName = new SimpleStringProperty(facultyLastName);
    }

	public String getFacultyQualification() {
		return facultyQualification.get();
	}

	public void setFacultyQualification(String facultyQualification) {
		this.facultyQualification = new SimpleStringProperty(facultyQualification);
	}
}