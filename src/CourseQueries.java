
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
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static ArrayList<CourseEntry> course = new ArrayList<CourseEntry>();
    private static PreparedStatement addCourses;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement getAllCourseCode;
    private static PreparedStatement getCourseSeat;
    private static PreparedStatement deleteCourses;
    private static ResultSet resultCourse;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourses = connection.prepareStatement("insert into app.course (semester,coursecode,discription,seats) values (?,?,?,?)");
            addCourses.setString(1, course.getSemester());
            addCourses.setString(2, course.getCourseCode());
            addCourses.setString(3, course.getDiscription());
            addCourses.setInt(4, course.getSeats());
            addCourses.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
        public static ArrayList<CourseEntry> getAllCourses(String Semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> AllClass = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select * from app.course where semester = ? order by semester");
            getAllCourses.setString(1,Semester);
            resultCourse = getAllCourses.executeQuery();
            
            while(resultCourse.next())
            {
                AllClass.add(new CourseEntry(resultCourse.getString("Semester"),resultCourse.getString("CourseCode"),resultCourse.getString("Discription"),resultCourse.getInt("Seats")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return AllClass;
        
    }
    
       public static ArrayList<String> getAllCourseCode (String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> Allcoursecode = new ArrayList<String>();
        try
        {
            getAllCourseCode = connection.prepareStatement("select coursecode from app.course where semester = ?");
            getAllCourseCode.setString(1,semester);
            resultCourse = getAllCourseCode.executeQuery();
            
            while(resultCourse.next())
            {
                Allcoursecode.add(resultCourse.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Allcoursecode;
       }
    
      public static int getCourseSeats(String semester, String courseCode){
          connection = DBConnection.getConnection();
          try{
              getCourseSeat = connection.prepareStatement("select seats from app.course where semester = ? and coursecode = ?");
              getCourseSeat.setString(1,semester);
              getCourseSeat.setString(2, courseCode);
              resultCourse = getCourseSeat.executeQuery();
              resultCourse.next();
              return resultCourse.getInt(1);
              
          }
          catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return 0;
      }
      
       public static void dropCourse(String semester,String coursecode){
        connection = DBConnection.getConnection();
        try
        {
            deleteCourses = connection.prepareStatement("delete from app.course where semester = ? and coursecode = ?");
            deleteCourses.setString(1, semester);
            deleteCourses.setString(2, coursecode);
            deleteCourses.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
     }
       
}
