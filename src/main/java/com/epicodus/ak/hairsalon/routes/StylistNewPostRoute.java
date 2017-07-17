package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.model.Stylist;
import spark.Request;

public class StylistNewPostRoute extends PostRoute {
    @Override
    protected void process(Request request) {
        Stylist stylist = new Stylist();
        stylist.setFirstName(request.queryParams("firstname"));
        stylist.setLastName(request.queryParams("lastname"));
        getDatabase().updateStylist(stylist);
    }
}
