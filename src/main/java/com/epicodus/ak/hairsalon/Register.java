package com.epicodus.ak.hairsalon;

import com.epicodus.ak.hairsalon.routes.GetRoute;
import com.epicodus.ak.hairsalon.routes.PostRoute;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.velocity.VelocityTemplateEngine;

public class Register {
    public static void get(String path, GetRoute route, String template, String layout) {
        route.setTemplate(template);
        route.setLayout(layout);
        Spark.get(path, route, new VelocityTemplateEngine());
    }
    public static void post(String path, PostRoute route, String redirect) {
        route.setRedirect(redirect);
        Spark.post(path, route, new VelocityTemplateEngine());
    }

}
