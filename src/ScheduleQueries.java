
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uitra
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<ScheduleEntry> faculty = new ArrayList<ScheduleEntry>();
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSBID;
    private static PreparedStatement getSBCC;
    private static PreparedStatement getSchS;
    private static PreparedStatement getWaiS;
    private static PreparedStatement updateSE;
    private static PreparedStatement DSBC;
        private static PreparedStatement DSBS;
    private static ResultSet resultStudent;
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSchedule = connection.prepareStatement("insert into app.schedule (semester,coursecode,studentid,status,timestamp) values (?,?,?,?,?)");
            addSchedule.setString(1, entry.getSemester());
            addSchedule.setString(2, entry.getCourseCode());
            addSchedule.setString(3, entry.getStudentID());
            addSchedule.setString(4, entry.getStatus());
            addSchedule.setTimestamp(5, entry.getTimestamp());
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> SBS = new ArrayList<ScheduleEntry>();
        try
        {
            getSBID = connection.prepareStatement("select * from app.schedule where semester = ? and studentid = ?");
            getSBID.setString(1, semester);
            getSBID.setString(2,studentID);
            resultStudent = getSBID.executeQuery();
            
            while(resultStudent.next())
            {
                SBS.add(new ScheduleEntry(resultStudent.getString("Semester"),resultStudent.getString("CourseCode"),resultStudent.getString("StudentID"),
                         resultStudent.getString("status"),resultStudent.getTimestamp("Timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return SBS;
        
    }
    
    public static int getScheduleStudentCount(String currentSemester,String courseCode){
        connection = DBConnection.getConnection();
        int count = 0;
        try{
            getSBCC = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
            getSBCC.setString(1, currentSemester);
            getSBCC.setString(2,courseCode);
            resultStudent = getSBCC.executeQuery();
            resultStudent.next();
            count = resultStudent.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
        public static ArrayList<ScheduleEntry> getScheduledStudentByCourse(String semester, String coursecode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> GSS = new ArrayList<ScheduleEntry>();
        try{
            getSchS = connection.prepareStatement("select * from app.schedule where semester = ? and coursecode = ? and status = 'S'");
            getSchS.setString(1, semester);
            getSchS.setString(2,coursecode);
            resultStudent = getSchS.executeQuery();
            while(resultStudent.next())
            {
                GSS.add(new ScheduleEntry(resultStudent.getString("Semester"),resultStudent.getString("CourseCode"),resultStudent.getString("StudentID"),
                         resultStudent.getString("status"),resultStudent.getTimestamp("Timestamp")));
            }
        }
                catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return GSS;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentByCourse(String semester, String coursecode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> GWS = new ArrayList<ScheduleEntry>();
        try{
            getWaiS = connection.prepareStatement("select * from app.schedule where semester = ? and coursecode = ? and status = 'W'");
            getWaiS.setString(1, semester);
            getWaiS.setString(2,coursecode);
            resultStudent = getWaiS.executeQuery();
            while(resultStudent.next())
            {
                GWS.add(new ScheduleEntry(resultStudent.getString("Semester"),resultStudent.getString("CourseCode"),resultStudent.getString("StudentID"),
                         resultStudent.getString("status"),resultStudent.getTimestamp("Timestamp")));
            }
        }
                catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return GWS;
    }
        
    public static void updateScheduleEntry(String semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateSE = connection.prepareStatement("UPDATE app.schedule Set status = 'S' where semester = ? and coursecode = ? and studentid = ?");
            updateSE.setString(1, entry.getSemester());
            updateSE.setString(2, entry.getCourseCode());
            updateSE.setString(3, entry.getStudentID());
            updateSE.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void DropSchByCourse(String semester,String coursecode){
        connection = DBConnection.getConnection();
        try
        {
            DSBC = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            DSBC.setString(1, semester);
            DSBC.setString(2, coursecode);
            DSBC.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
     }
    
    public static void DropSchByStudent(String studentid){
        connection = DBConnection.getConnection();
        try
        {
            DSBS = connection.prepareStatement("delete from app.schedule where studentid = ?");
            DSBS.setString(1, studentid);
            DSBS.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
     }
}
