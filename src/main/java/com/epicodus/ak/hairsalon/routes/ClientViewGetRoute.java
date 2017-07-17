package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.model.Client;
import spark.Request;

public class ClientViewGetRoute extends GetRoute {
    @Override
    protected void process(Request request) throws Exception {
        Client client = null;
        try {
            int clientId = Integer.parseInt(request.params("id"));
            client = getDatabase().getClientById(clientId);
        } catch (NumberFormatException e) {
        }
        if (client == null) {
            throw new Exception(String.format("no client with id %s", request.params("id")));
        }
        addToModel("client", client);
        addToModel("stylist", client.getStylistId() == null ? null : getDatabase().getStylistById(client.getStylistId()));
        addToModel("appointments", getDatabase().getAppointmentsByClient(client.getId()));
    }
}
