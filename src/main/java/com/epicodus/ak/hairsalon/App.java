package com.epicodus.ak.hairsalon;

import com.epicodus.ak.hairsalon.db.Database;
import com.epicodus.ak.hairsalon.db.PgDatabase;
import com.epicodus.ak.hairsalon.model.Stylist;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args){
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.parseInt(System.getenv("PORT")));
        }

        Spark.get("/", (request, response) -> {

            Database db = new PgDatabase();
            Stylist stylist = new Stylist();
            stylist.setFirstName("Ivan");
            stylist.setLastName("Ivanov");
            db.updateStylist(stylist);


            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
