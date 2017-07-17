package com.epicodus.ak.hairsalon.routes;

import spark.Request;

public class ClientDeletePostRoute extends PostRoute{
    @Override
    protected void process(Request request) {
        int clientId = Integer.parseInt(request.params("id"));
        getDatabase().deleteClient(clientId);
    }
}
