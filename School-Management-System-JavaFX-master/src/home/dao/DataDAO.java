package home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import home.utility.DatabaseUtil;

public class DataDAO {

	private Connection con = null;
	private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement preparedStatement = null;

	public ResultSet getStudentsData() throws SQLException {
		con = DatabaseUtil.getConnection();
		statement = con.createStatement();
		rs = statement.executeQuery("SELECT * FROM student");
		return rs;
	}

	public ResultSet getClassAttendanceData() throws SQLException {
		con = DatabaseUtil.getConnection();
		statement = con.createStatement();
		rs = statement.executeQuery("SELECT * FROM class_attendance_data");
		return rs;
	}

	public ResultSet getStudentsCount() throws SQLException {
		con = DatabaseUtil.getConnection();
		statement = con.createStatement();
		rs = statement.executeQuery("SELECT count(*) AS total_students FROM student");
		return rs;
	}

	public ResultSet getDashboardDetails() throws SQLException {
		con = DatabaseUtil.getConnection();
		preparedStatement = con.prepareStatement(
				"SELECT SUM(hours) AS total_hours, COUNT(DISTINCT lesson_id) AS total_lessons, COUNT(*) AS missed_classes FROM student_attendance");
		return preparedStatement.executeQuery();
	}

	public int insertStudent(String fname, String lname, String standard, String section, String rollNumber)
			throws SQLException {
		con = DatabaseUtil.getConnection();

		preparedStatement = con.prepareStatement(
				"insert into student(firstname, lastname, standard, section, rollnumber) values(?, ?, ?, ?, ?)");
		preparedStatement.setString(1, fname);
		preparedStatement.setString(2, lname);
		preparedStatement.setString(3, standard);
		preparedStatement.setString(4, section);
		preparedStatement.setString(5, rollNumber);

		return preparedStatement.executeUpdate();
	}

	public int updateStudent(String firstName, String lastName, String studentClass, String section,
			String rollNumber, int id) throws SQLException {
		
		con = DatabaseUtil.getConnection();

		preparedStatement = con.prepareStatement(
				"Update student set firstname = ?, lastname = ?, standard = ?, section = ?, rollnumber =? where studentid = ?");
		preparedStatement.setString(1, firstName);
		preparedStatement.setString(2, lastName);
		preparedStatement.setString(3, studentClass);
		preparedStatement.setString(4, section);
		preparedStatement.setString(5, rollNumber);
		preparedStatement.setInt(6, id);

		return preparedStatement.executeUpdate();
	}

	public ResultSet getFacultysData() throws SQLException {
		con = DatabaseUtil.getConnection();
		statement = con.createStatement();
		rs = statement.executeQuery("SELECT * FROM teacher_details");
		return rs;
	}

	public int insertFaculty(String firstName, String lastName, String qualification) throws SQLException {
		con = DatabaseUtil.getConnection();

		preparedStatement = con.prepareStatement(
				"INSERT INTO teacher_details (facultyFirstName, facultyLastName, facultyQualification)\r\n"
				+ "VALUES(?, ?, ?)");
		preparedStatement.setString(1, firstName);
		preparedStatement.setString(2, lastName);
		preparedStatement.setString(3, qualification);

		return preparedStatement.executeUpdate();
	}
}
