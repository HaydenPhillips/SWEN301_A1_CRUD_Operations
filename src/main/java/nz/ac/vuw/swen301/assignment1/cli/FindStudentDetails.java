package nz.ac.vuw.swen301.assignment1.cli;

import nz.ac.vuw.swen301.assignment1.Degree;
import nz.ac.vuw.swen301.assignment1.Student;
import nz.ac.vuw.swen301.assignment1.StudentManager;

public class FindStudentDetails {

    // THE FOLLOWING METHOD MUST IMPLEMENTED
    /**
     * Executable: the user will provide a student id as single argument, and if the details are found,
     * the respective details are printed to the console.
     * E.g. a user will invoke this by running "java -cp <someclasspath> nz.ac.vuw.swen301.assignment1.cli.FindStudentDetails id42"
     * @param arg
     */
    public static void main (String[] arg) {
        StudentManager.update(new Student("id420", "Scott", "M", new Degree("deg8", "scott")));
        StudentManager.readStudent("id420");
        StudentManager.readDegree("deg4");
//        StudentManager.delete();
        StudentManager.readStudent("id420");
    }
}
