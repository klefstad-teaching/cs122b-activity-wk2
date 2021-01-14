package edu.uci.ics.cs122b.activity2.core;

import edu.uci.ics.cs122b.activity2.ActivityService;
import edu.uci.ics.cs122b.activity2.logger.ServiceLogger;
import edu.uci.ics.cs122b.activity2.models.StudentRequestModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewStudentRecords {

    /*
    student
        - id
        - firstName
        - lastName
        - email
        - gpa
        - major

    create table if not exists student (
        id int not null auto_increment primary key,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        email varchar(50) not null,
        gpa decimal unsigned,
        major varchar(25)
    );
     */
    public static int insertNewStudentRecord(StudentRequestModel requestModel) {
        int code = 0;
        try {
            // Construct the query
            String query =  "INSERT INTO student (first_name, last_name, email, gpa, major)" +
                    " VALUE (?, ?, ?, ?, ?)";

            // Create the prepared statement
            PreparedStatement ps = ActivityService.getCon().prepareStatement(query);

            // Set the arguments
            ps.setString(1, requestModel.getFirstName());
            ps.setString(2, requestModel.getLastName());
            ps.setString(3, requestModel.getEmail());
            ps.setFloat(4, requestModel.getGpa());
            ps.setString(5, requestModel.getMajor());

            // Save the query result to a ResultSet so records may be retrieved
            ServiceLogger.LOGGER.info("Trying insertion: " + ps.toString());
            code = ps.executeUpdate();
            ServiceLogger.LOGGER.info("Insertion succeeded.");

        } catch (SQLException e) {
            ServiceLogger.LOGGER.warning("Insertion failed.");
            e.printStackTrace();
            return -1;
        }
        return code;
    }

    /*
    ENDPOINT can query for:
        - firstName like
        - lastName like
        - minimum gpa
        - major (=)
    ENDPOINT must return at least id
     */

    /*
    SELECT id, lastName, firstName, GPA
    FROM student
    WHERE lastName LIKE 'j%'
     */

    public static String buildStudentQuery(ArrayList<String> cols,
                                           StudentRequestModel requestModel){

        String SELECT = "SELECT id";
        String FROM = " FROM student";
        String WHERE = " WHERE 1=1";

        for(String c:cols){
            SELECT += (", " + c);
        }

        if (requestModel.getFirstName() != null) {
            WHERE += " && first_name LIKE '%" + requestModel.getFirstName() + "%'";
        }

        if (requestModel.getLastName() != null) {
            WHERE += " && last_name LIKE '%" + requestModel.getLastName() + "%'";
        }

        if (requestModel.getGpa() != null) {
            WHERE += " && gpa > " + requestModel.getGpa();
        }

        if (requestModel.getMajor() != null) {
            WHERE += " && major = " + requestModel.getMajor();
        }

        return SELECT + FROM + WHERE;
    }
}