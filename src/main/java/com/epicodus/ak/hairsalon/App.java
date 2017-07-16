package com.epicodus.ak.hairsalon;

import com.epicodus.ak.hairsalon.routes.*;
import spark.Spark;

public class App {
    public static void main(String[] args){
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.parseInt(System.getenv("PORT")));
        }

        String defaultLayout = "templates/layout.vtl";

        Register.get("/", new SimpleGetRoute(),"templates/index.vtl", defaultLayout );

        Register.get("/stylists", new StylistsGetRoute(),"templates/stylists.vtl", defaultLayout);
        Register.get("/stylists/new", new SimpleGetRoute(),"templates/stylist-new.vtl",defaultLayout);
        Register.get("/stylists/:id/view", new StylistViewGetRoute(), "templates/stylist-view.vtl", defaultLayout);
        Register.get("/stylists/:id", new StylistEditGetRoute(),"templates/stylist-edit.vtl",defaultLayout);

        Register.post("/stylists/new", new StylistNewPostRoute(),"/stylists");
        Register.post("/stylists/:id", new StylistEditPostRoute(), "/stylists");
        Register.post("/stylists/:id/delete", new StylistDeletePostRoute(), "/stylists");
    }

}
