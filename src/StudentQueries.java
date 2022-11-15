
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
public class StudentQueries {
    private static Connection connection;
    private static ArrayList<StudentEntry> faculty = new ArrayList<StudentEntry>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudent;
    private static PreparedStatement getStudentByID;
    private static PreparedStatement deleteStudent;
    private static ResultSet resultStudent;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (firstname,lastname,studentid) values (?,?,?)");
            addStudent.setString(1, student.getFirstName());
            addStudent.setString(2, student.getLastName());
            addStudent.setString(3, student.getStudentID());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudent()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> AllStu = new ArrayList<StudentEntry>();
        try
        {
            getAllStudent = connection.prepareStatement("select * from app.student");
            resultStudent = getAllStudent.executeQuery();
            
            while(resultStudent.next())
            {
                AllStu.add(new StudentEntry(resultStudent.getString("FirstName"),resultStudent.getString("LastName"),resultStudent.getString("StudentID")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return AllStu;
        
    }
    
        public static StudentEntry getStudent(String studentID){
         connection = DBConnection.getConnection();
         StudentEntry current = null;
         try{
             getStudentByID = connection.prepareStatement("select firstname,lastname,studentid from app.student where studentid = ?");
             getStudentByID.setString(1,studentID);
            resultStudent = getStudentByID.executeQuery();
            
            while(resultStudent.next())
            {
                current = new StudentEntry(resultStudent.getString(1), resultStudent.getString(2), resultStudent.getString(3));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return current;
       }


    
    public static void dropStudent(String studentid){
        connection = DBConnection.getConnection();
        try
        {
            deleteStudent = connection.prepareStatement("delete from app.student where studentid = ?");
            deleteStudent.setString(1, studentid);
            deleteStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
     }
}
