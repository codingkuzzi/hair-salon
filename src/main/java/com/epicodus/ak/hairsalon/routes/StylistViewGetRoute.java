package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class StylistViewGetRoute extends GetRoute {
    @Override
    protected void process(Request request) {
        int stylistId = Integer.parseInt(request.params("id"));
        addToModel("stylist", getDatabase().getStylistById(stylistId));
        addToModel("appointments", getDatabase().getAppointmentsByStylist(stylistId));
        addToModel("clients", getDatabase().getClientsByStylist(stylistId));
    }
}
