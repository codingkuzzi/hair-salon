package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class StylistDeletePostRoute extends PostRoute {
    @Override
    protected void process(Request request) {
        int stylistId = Integer.parseInt(request.params("id"));
        getDatabase().deleteStylist(stylistId);
    }
}
