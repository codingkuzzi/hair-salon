package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.db.PgDatabase;
import spark.Request;

public class StylistViewGetRoute extends GetRoute {
    @Override
    protected void process(Request request) {
        int stylistId = Integer.parseInt(request.params("id"));
        Database db = new PgDatabase();
        addToModel("stylist", db.getStylistById(stylistId));
        addToModel("appointments", db.getAppointmentsByStylist(stylistId));
        addToModel("clients", db.getClientsByStylist(stylistId));
    }
}
