package nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.studentmemdb.StudentDB;
import java.util.Set;

/**
 * A student managers creates student instances from rows in the database.
 * @author jens dietrich
 */
public class StudentManager {

    //  DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    // THE FOLLOWING METHOD MUST IMPLEMENTED
    /**
     * Return a student instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * @param id
     * @return
     */
    public static Student findById(String id) {
        return null;
    }

}
