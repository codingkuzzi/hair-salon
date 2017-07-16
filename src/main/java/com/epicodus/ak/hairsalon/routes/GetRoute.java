package com.epicodus.ak.hairsalon.routes;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public abstract class GetRoute implements TemplateViewRoute {
    private String layout;
    private Map<String, Object> model;

    public GetRoute() {
        this.model = new HashMap<>();
    }

    public void setTemplate(String template) {
        model.put("template", template);
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    protected void addToModel(String key, Object value){
        model.put(key, value);
    }

    protected abstract void process(Request request);

    public ModelAndView handle(Request request, Response response) throws Exception {
        process(request);
        return new ModelAndView(model, layout);
    }
}