package edu.uci.ics.cs122b.activity.wk2.core;

import edu.uci.ics.cs122b.activity.wk2.ActivityService;
import edu.uci.ics.cs122b.activity.wk2.logger.ServiceLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DISCLAIMER: This code will not work without a proper database to accompany it.
//             It is for demonstration purposes only!

public class StudentRecords {
    public static void retrieveStudentsFromDB(String name) {
        try {
            // Construct the query
            String query =  "SELECT id, email, firstName, lastName, GPA" +
                            " FROM students" +
                            " WHERE firstName LIKE ? OR lastName LIKE ?;";

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
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Float gpa = rs.getFloat("GPA");
                ServiceLogger.LOGGER.info("Retrieved student");
            }

        } catch (SQLException e) {
            ServiceLogger.LOGGER.warning("Query failed: Unable to retrieve student records.");
            e.printStackTrace();
        }
    }


}


