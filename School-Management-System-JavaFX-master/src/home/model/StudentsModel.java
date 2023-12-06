package home.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentsModel {

    private SimpleIntegerProperty studentId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty standard;
    private SimpleStringProperty section;
    private SimpleStringProperty rollNumber;

    public StudentsModel(Integer studentId, String firstName, String lastName, String standard, String section, String rollnumber) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.standard = new SimpleStringProperty(standard);
        this.section = new SimpleStringProperty(section);
        this.rollNumber = new SimpleStringProperty(rollnumber);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId = new SimpleIntegerProperty(studentId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

	public String getStandard() {
		return standard.get();
	}

	public void setStandard(String standard) {
		this.standard = new SimpleStringProperty(standard);
	}

	public String getSection() {
		return section.get();
	}

	public void setSection(String section) {
		this.section = new SimpleStringProperty(section);
	}

	public String getRollNumber() {
		return rollNumber.get();
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = new SimpleStringProperty(rollNumber);
	}
}