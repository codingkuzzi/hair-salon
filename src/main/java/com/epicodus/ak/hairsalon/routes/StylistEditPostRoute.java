package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.db.PgDatabase;
import com.epicodus.ak.hairsalon.model.Stylist;
import spark.Request;

public class StylistEditPostRoute extends PostRoute {
    @Override
    protected void process(Request request) {
        int stylistId = Integer.parseInt(request.params("id"));
        Database db = new PgDatabase();
        Stylist stylist = db.getStylistById(stylistId);
        stylist.setFirstName(request.queryParams("firstname"));
        stylist.setLastName(request.queryParams("lastname"));
        db.updateStylist(stylist);
    }
}
