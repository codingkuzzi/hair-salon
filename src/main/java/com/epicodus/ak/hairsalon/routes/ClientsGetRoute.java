package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class ClientsGetRoute extends GetRoute {
    @Override
    protected void process(Request request) throws Exception {
        addToModel("clients", getDatabase().getClients());
    }
}
