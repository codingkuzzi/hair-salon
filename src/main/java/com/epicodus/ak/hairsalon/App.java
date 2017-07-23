package com.epicodus.ak.hairsalon;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.db.PgDatabase;
import com.epicodus.ak.hairsalon.routes.*;
import spark.Spark;

import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args){
        // Use Heroku environment variables, if detected
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.parseInt(System.getenv("PORT")));
        }
        Database database;
        if (System.getenv("JDBC_DATABASE_URL") != null) {
            database = new PgDatabase(
                System.getenv("JDBC_DATABASE_URL"),
                System.getenv("JDBC_DATABASE_USERNAME"),
                System.getenv("JDBC_DATABASE_PASSWORD"));
        } else {
            // TODO: move local database setting to some config file
            database = new PgDatabase(
                "jdbc:postgresql://localhost:5432/hair-salon",
                "postgres",
                "postgres");
        }

        staticFileLocation("/public");

        Register register = new Register(database, "templates/layout.vtl");

        register.get("/", new SimpleGetRoute(),"templates/index.vtl");

        register.get("/stylists", new StylistsGetRoute(),"templates/stylists.vtl");
        register.get("/stylists/new", new SimpleGetRoute(),"templates/stylist-new.vtl");
        register.get("/stylists/:id/view", new StylistViewGetRoute(), "templates/stylist-view.vtl");
        register.get("/stylists/:id", new StylistEditGetRoute(),"templates/stylist-edit.vtl");

        register.post("/stylists/new", new StylistNewPostRoute(),"/stylists");
        register.post("/stylists/:id", new StylistEditPostRoute(), "/stylists");
        register.post("/stylists/:id/delete", new StylistDeletePostRoute(), "/stylists");

        register.get("/clients", new ClientsGetRoute(), "templates/clients.vtl");
        register.get("/clients/new", new ClientNewGetRoute(), "templates/client-new.vtl");
        register.get("/clients/:id/view", new ClientViewGetRoute(), "templates/client-view.vtl");
        register.get("/clients/:id", new ClientEditGetRoute(), "templates/client-edit.vtl");

        register.post("/clients/new", new ClientNewPostRoute(), "/clients");
        register.post("/clients/:id", new ClientEditPostRoute(), "/clients");
        register.post("/clients/:id/delete", new ClientDeletePostRoute(), "/clients" );
    }

}
