package com.epicodus.ak.hairsalon;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.routes.GetRoute;
import com.epicodus.ak.hairsalon.routes.PostRoute;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

public class Register {
    private Database database;
    private String defaultLayout;

    public Register(Database database, String defaultLayout){
        this.database = database;
        this.defaultLayout = defaultLayout;
    }

    public void get(String path, GetRoute route, String template) {
        route.setTemplate(template);
        route.setLayout(defaultLayout);
        route.setDatabase(database);
        Spark.get(path, route, new VelocityTemplateEngine());
    }
    public void post(String path, PostRoute route, String redirect) {
        route.setRedirect(redirect);
        route.setDatabase(database);
        Spark.post(path, route, new VelocityTemplateEngine());
    }
}
