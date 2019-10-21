package edu.uci.ics.cs122b.activity.wk2.resources;



import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uci.ics.cs122b.activity.wk2.logger.ServiceLogger;
import edu.uci.ics.cs122b.activity.wk2.models.SaltAndHashRequestModel;
import edu.uci.ics.cs122b.activity.wk2.models.SaltAndHashResponseModel;
import edu.uci.ics.cs122b.activity.wk2.models.SessionRequestModel;
import edu.uci.ics.cs122b.activity.wk2.models.SessionResponseModel;
import edu.uci.ics.cs122b.activity.wk2.security.Crypto;
import edu.uci.ics.cs122b.activity.wk2.security.Session;
import org.apache.commons.codec.binary.Hex;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("example") // Outer path
public class ActivityPage {

    @Path("crypto")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // Example endpoint to demonstrate salting & hashing
    public Response saltAndHash(@Context HttpHeaders headers, String jsonText) {
        SaltAndHashRequestModel requestModel;
        SaltAndHashResponseModel responseModel;
        ObjectMapper mapper = new ObjectMapper();

        // Validate model & map JSON to POJO
        try {
            requestModel = mapper.readValue(jsonText, SaltAndHashRequestModel.class);
        } catch (IOException e) {
            // Catch other exceptions here
            e.printStackTrace();
            return null;
        }

        ServiceLogger.LOGGER.info("Received request to hash and salt and password");
        ServiceLogger.LOGGER.info("Request:\n" + jsonText);

        // Salt & Hash
        // Generate a random salt
        byte[] salt = Crypto.genSalt();

        // Use the salt to hash the password
        char[] password = requestModel.getPassword();
        byte[] hashedPassword = Crypto.hashPassword(password, salt, Crypto.ITERATIONS, Crypto.KEY_LENGTH);

        // Encode salt & password
        String encodedSalt = Hex.encodeHexString(salt);
        String encodedPassword = Hex.encodeHexString(hashedPassword);

        responseModel = new SaltAndHashResponseModel(10, "Successfully salted & hashed password.",
                encodedSalt, encodedPassword);
        return Response.status(Response.Status.OK).entity(responseModel).build();
    }

    @Path("session")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // Example endpoint to demonstrate session handling
    public Response createSession(@Context HttpHeaders headers, String jsonText) {
        SessionRequestModel requestModel;
        SessionResponseModel responseModel;
        ObjectMapper mapper = new ObjectMapper();

        // Validate model & map JSON to POJO
        try {
            requestModel = mapper.readValue(jsonText, SessionRequestModel.class);
        } catch (IOException e) {
            // Catch other exceptions here
            e.printStackTrace();
            return null;
        }

        ServiceLogger.LOGGER.info("Received request for session");
        ServiceLogger.LOGGER.info("Request:\n" + jsonText);

        // Create session
        Session session = Session.createSession(requestModel.getEmail());
        responseModel = new SessionResponseModel(11, "Successfully created session.",
                session.getSessionID().toString());

        return Response.status(Response.Status.OK).entity(responseModel).build();
    }



}
