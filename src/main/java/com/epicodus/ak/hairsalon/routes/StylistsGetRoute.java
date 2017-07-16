package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.db.PgDatabase;
import spark.Request;

public class StylistsGetRoute extends GetRoute {
    @Override
    protected void process(Request request) {
        Database db = new PgDatabase();
        addToModel("stylists", db.getStylists());
    }
}
