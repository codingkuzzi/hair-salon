package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class StylistsGetRoute extends GetRoute {
    @Override
    protected void process(Request request) {
        addToModel("stylists", getDatabase().getStylists());
    }
}
