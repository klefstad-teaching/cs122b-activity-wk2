package edu.uci.ics.cs122b.activity2.core;

import edu.uci.ics.cs122b.activity2.ActivityService;
import edu.uci.ics.cs122b.activity2.logger.ServiceLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DISCLAIMER: This code will not work without a proper database to accompany it.
//             It is for demonstration purposes only!

public class StudentRecords {
    public static void retrieveStudentsFromDB(String name) {
        try {
            // Construct the query
            String query =  "SELECT id, email, first_name, last_name, gpa" +
                            " FROM student" +
                            " WHERE first_name LIKE ? OR last_name LIKE ?;";

            // Create the prepared statement
            PreparedStatement ps = ActivityService.getCon().prepareStatement(query);

            // Set the arguments
            ps.setString(1, name);
            ps.setString(2, name);

            // Save the query result to a ResultSet so records may be retrieved
            ServiceLogger.LOGGER.info("Trying query: " + ps.toString());
            ResultSet rs = ps.executeQuery();
            // Use executeQuery() for queries that RETRIEVE from DB (returns ResultSet)
            // Use executeUpdate() for queries that CHANGE the DB (returns # of rows modified as int)
            // Use execute() for general purpose queries
            ServiceLogger.LOGGER.info("Query succeeded.");

            // Retrieve the students from the Result Set
            // ResultSets are like iterators (they start from BEFORE the first result)
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String email = rs.getString("email");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Float gpa = rs.getFloat("gpa");
                ServiceLogger.LOGGER.info("Retrieved student: (" + id + "," + email+" )");
            }

        } catch (SQLException e) {
            ServiceLogger.LOGGER.warning("Query failed: Unable to retrieve student records.");
            e.printStackTrace();
        }
    }


}


