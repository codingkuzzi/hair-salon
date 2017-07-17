package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.model.Stylist;
import spark.Request;

public class StylistEditGetRoute extends GetRoute {
    @Override
    protected void process(Request request) throws Exception {
        Stylist stylist = null;
        try {
            int stylistId = Integer.parseInt(request.params("id"));
            stylist = getDatabase().getStylistById(stylistId);
        }
        catch (NumberFormatException e) {
        }
        if (stylist == null) {
            throw new Exception(String.format("no stylist with id %s", request.params("id")));
        }
        addToModel("stylist", stylist);
        addToModel("hasAppointments", getDatabase().anyAppointmentByStylist(stylist.getId()));
    }
}