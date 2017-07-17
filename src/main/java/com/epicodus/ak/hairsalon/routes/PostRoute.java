package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.db.Database;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public abstract class PostRoute implements TemplateViewRoute {
    private String redirect;
    private Database database;

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    protected Database getDatabase() {
        return database;
    }

    protected abstract void process(Request request);

    @Override
    public ModelAndView handle(Request request, Response response) {
        process(request);
        response.redirect(redirect);
        return new ModelAndView(null, null);
    }
}
