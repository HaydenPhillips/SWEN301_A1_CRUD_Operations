package test.nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.assignment1.Degree;
import nz.ac.vuw.swen301.assignment1.Student;
import nz.ac.vuw.swen301.assignment1.StudentManager;
import nz.ac.vuw.swen301.studentmemdb.StudentDB;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class StudentManagerTest {

    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // INCLUDE THIS AS A STATIC FIXTURE (annotated with @BeforeClass) IN ALL TESTS
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    @BeforeClass
    public static void init () {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    @Test
    public void dummyTest() throws Exception {
        Student s = StudentManager.readStudent("id420");
        assert s != null;
        s.setFirstName("Scotty");
        StudentManager.update(s);
        assertTrue(StudentManager.readStudent("id420").getFirstName().equals("Scotty"));

    }

    @Test
    public void truncationErrorTest() throws Exception {
        Student s = StudentManager.readStudent("id420");
        assert s != null;
        StudentManager.update(s);
        String sFirstName = s.getFirstName();
        assertTrue(sFirstName.length() <= 10);
    }

    @Test
    public void testCreate01(){
        Degree degree = StudentManager.readDegree("deg4");
        assert degree != null;
        StudentManager.createStudent("DoWang","Scotty", degree);
        assertNotNull(StudentManager.readStudent("id10000"));
    }


    @Test
    public void testReadStudent() throws Exception {
        Student student = StudentManager.readStudent("id420");
        assert student != null;
        assertTrue(student.getDegree().getId().equals("deg4"));
    }

    @Test
    public void testReadDegree() throws Exception {
        Degree deg = StudentManager.readDegree("deg1");
        assert deg != null;
        assertTrue(deg.getName().equals("BSc Computer Graphics"));
    }


    @Test
    public void testUpdateStudent(){
        Student originalStudent = StudentManager.readStudent("id69");
        assert originalStudent!= null;
        originalStudent.setFirstName("Scotty");
        StudentManager.update(originalStudent);
        Student changedStudent = StudentManager.readStudent("id69");
        assert changedStudent != null;
        assertTrue(changedStudent.getFirstName().equals("Scotty"));
    }


    @Test
    public void testAllStudentID() throws Exception {
        Collection<String> allStudents = StudentManager.getAllStudentIds();
        assert allStudents != null;
        int count = 0;
        for (String s : allStudents) {
            assertEquals(s,("id"+(count++)));
        }
    }

    @Test
    public void testAllDegreeIDs01() throws Exception {
        ArrayList<String> allDegrees = (ArrayList<String>) StudentManager.getAllDegreeIds();
        assert allDegrees != null;
        assertTrue(allDegrees.size() == 16);
    }


    @Test
    public void testAllDegreeIDs02() throws Exception {
        ArrayList<String> allDegrees = (ArrayList<String>) StudentManager.getAllDegreeIds();
        assert allDegrees != null;
        int count = 0;
        for (String s : allDegrees) {
            assertTrue(s.equals("deg"+(count++)));
        }
    }

    @Test
    public void timeTest(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            StudentManager.readStudent("id"+ (int)(Math.random()*10000));
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime-startTime;
        System.out.println("Time taken to read 1000 queries: " + totalTime);
        assertTrue(totalTime <= 1000);
    }

}