package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.model.Stylist;
import spark.Request;

public class StylistEditPostRoute extends PostRoute {
    @Override
    protected void process(Request request) {
        int stylistId = Integer.parseInt(request.params("id"));
        Stylist stylist = getDatabase().getStylistById(stylistId);
        stylist.setFirstName(request.queryParams("firstname"));
        stylist.setLastName(request.queryParams("lastname"));
        getDatabase().updateStylist(stylist);
    }
}
