package com.epicodus.ak.hairsalon.routes;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public abstract class PostRoute implements TemplateViewRoute {
    private String redirect;

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    protected abstract void process(Request request);

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        process(request);
        response.redirect(redirect);
        return new ModelAndView(null, null);
    }
}
