package edu.uci.ics.cs122b.activity2.core;

import edu.uci.ics.cs122b.activity2.models.StudentRequestModel;

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
     */

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
            WHERE += " && firstName LIKE '%" + requestModel.getFirstName() + "%'";
        }

        if (requestModel.getLastName() != null) {
            WHERE += " && lastName LIKE '%" + requestModel.getLastName() + "%'";
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