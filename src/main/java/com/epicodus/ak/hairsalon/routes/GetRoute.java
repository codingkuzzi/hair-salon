package com.epicodus.ak.hairsalon.routes;

import com.epicodus.ak.hairsalon.db.Database;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class GetRoute implements TemplateViewRoute {
    private String layout;
    private String template;
    private Map<String, Object> model;
    private Database database;

    public GetRoute() {
        this.model = new HashMap<>();
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    protected Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    protected void addToModel(String key, Object value) {
        model.put(key, value);
    }

    protected abstract void process(Request request) throws Exception;

    public ModelAndView handle(Request request, Response response) {
        try {
            model.clear();
            process(request);
            model.put("template", template);
            return new ModelAndView(model, layout);
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            model.clear();
            model.put("error", e.getMessage());
            model.put("stack", sw.toString());
            model.put("template", "templates/error.vtl");
            return new ModelAndView(model, layout);
        }
    }
}