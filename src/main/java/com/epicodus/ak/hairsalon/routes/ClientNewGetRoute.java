package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class ClientNewGetRoute extends GetRoute{
    @Override
    protected void process(Request request) throws Exception {
        addToModel("stylists", getDatabase().getStylists());
    }
}
