package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.model.Client;
import spark.Request;

import java.time.LocalDate;

public class ClientNewPostRoute extends PostRoute {
    @Override
    protected void process(Request request) {
        String lastName = request.queryParams("lastname");
        String firstName = request.queryParams("firstname");
        String gender = request.queryParams("gender");
        LocalDate dateOfBirth = LocalDate.parse(request.queryParams("dateofbirth"));
        int stylistId = Integer.parseInt(request.queryParams("stylist"));

        Client client = new Client();
        client.setLastName(lastName);
        client.setFirstName(firstName);
        client.setGender(gender);
        client.setDateOfBirth(dateOfBirth);
        client.setStylistId(stylistId > 0 ? stylistId : null);

        getDatabase().updateClient(client);
    }
}
