/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uitra
 */
import java.sql.Timestamp;

public class ScheduleEntry {
    private String Semester;
    private String CourseCode;
    private String StudentID;
    private String Status;
    private Timestamp Timestamp;

    public ScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.Status = Status;
        this.Timestamp = Timestamp;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getStatus() {
        return Status;
    }

    public Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setTimestamp(Timestamp Timestamp) {
        this.Timestamp = Timestamp;
    }
    
    
}
