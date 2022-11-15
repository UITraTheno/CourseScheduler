/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uitra
 */
public class CourseEntry {
    private String Semester;
    private String CourseCode;
    private String Discription;
    private int Seats;

    public CourseEntry(String Semester, String CourseCode, String Discription, int Seats) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.Discription = Discription;
        this.Seats = Seats;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getDiscription() {
        return Discription;
    }

    public int getSeats() {
        return Seats;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public void setSeats(int Seats) {
        this.Seats = Seats;
    }

    
    
}
