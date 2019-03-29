package nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.studentmemdb.StudentDB;

import java.sql.*;
import java.util.*;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read operation for instanbces of Degree.
 *
 * @author jens dietrich
 */
public class StudentManager {

    public static int availableID = 10000;
    public static HashMap<String, Student> hashM = new HashMap<>();
    private static Connection connection;

    //  DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        StudentDB.init();
        try {
            connection = DriverManager.getConnection("jdbc:derby:memory:student_records");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    // THE FOLLOWING METHODS MUST IMPLEMENTED :

    /**
     * Return a student instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     *
     * @param id
     * @return
     */
    public static Student readStudent(String id) {

        if (hashM.containsKey(id)) {
            Student s = hashM.get(id);
            return s;
        }

        try (Statement statement = connection.createStatement()) {

            String sql = "select * from students where id = '" + id + "'";
            ResultSet result = statement.executeQuery(sql);


            while (result.next()) {             // id,first_name,name,degree (all strings), ids range
                String sid = result.getString("id");
                String first_name = result.getString("first_name");
                String name = result.getString("name");
                String degree = result.getString("degree");

                Student student = new Student(sid, name, first_name, new Degree(degree, name));
                hashM.put(id, student);
//                System.out.println(student.getId()+ "-"+student.getName()+"-"+student.getFirstName()+"-"+student.getDegree().getId());
                return student;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Return a degree instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     *
     * @param id
     * @return
     */
    public static Degree readDegree(String id) {

        try {
            Statement statement = connection.createStatement();

            String sql = "select * from degrees where id = '" + id + "'";
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) { // id,first_name,name,degree (all strings), ids range
                String degreeID = result.getString("id");
                String name = result.getString("name");

                Degree degree = new Degree(degreeID, name);
                return degree;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Delete a student instance from the database.
     * I.e., after this, trying to read a student with this id will return null.
     *
     * @param student
     */
    public static void delete(Student student) {

        try (Statement statement = connection.createStatement()) {
            String sid = student.getId();

            String sql = "delete from students where id='" + sid + "'";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update (synchronize) a student instance with the database.
     * The id will not be changed, but the respective database record  may have changed names, first names or degree.
     * Note that names and first names can only be max 1o characters long.                      //test for this
     *
     * @param student
     */
    public static void update(Student student) {

        if (student.getFirstName().length() > 10) {
            student.setFirstName(student.getFirstName().substring(0, 9));
        }
        if (student.getName().length() > 10) {
            student.setFirstName(student.getName().substring(0, 9));
        }

        try (Statement statement = connection.createStatement()) {
            String sid = student.getId();
            String name = student.getName();
            String firstName = student.getFirstName();
            Degree degree = student.getDegree();


            String sql = "UPDATE STUDENTS SET  first_name='" + firstName + "' , name='" + name + "', degree='" + degree.getId() + "' where id= '" + sid + "'";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new student with the values provided, and save it to the database.
     * The student must have a new id that is not been used by any other Student instance or STUDENTS record (row).
     *
     * @param name
     * @param firstName
     * @param degree
     * @return a freshly created student instance
     */

    public static Student createStudent(String name, String firstName, Degree degree) {

        try (Statement statement = connection.createStatement()) {

            String sql = "INSERT INTO STUDENTS (id, name, first_name, degree) VALUES ('id" + (availableID++) + "', '" + name + "', '" + firstName + "', '" + degree.getId() + "')";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all student ids currently being used in the database.
     *
     * @return
     */
    public static Collection<String> getAllStudentIds() {

        try (Statement statement = connection.createStatement()) {

            String sql = "select * from STUDENTS";
            ResultSet result = statement.executeQuery(sql);

            ArrayList<String> allStudents = new ArrayList<>();
            while (result.next()) {
                allStudents.add(result.getString("id"));
            }
            System.out.println(allStudents);
            return allStudents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all degree ids currently being used in the database.
     *
     * @return
     */
    public static Iterable<String> getAllDegreeIds() {

            try (Statement statement = connection.createStatement()) {

                String sql = "select * from DEGREES";
                ResultSet degreeResult = statement.executeQuery(sql);

                ArrayList<String> allDegrees = new ArrayList<>();
                while (degreeResult.next()) {
                    allDegrees.add(degreeResult.getString("id"));
                }
                return allDegrees;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
