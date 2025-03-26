package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;
import lombok.extern.java.Log;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx){
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page",page));
    }

    public static void root(Context ctx){
        var page = new MainPage(ctx.sessionAttribute("name"));
        ctx.render("index.jte", model("page", page));
    }

    public static void login(Context ctx){
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");
        User user = UsersRepository.findByName(name).orElse(null);
        if (user != null && user.getPassword().equals(encrypt(password))){
            ctx.sessionAttribute("name", name);
            ctx.redirect("/");
        }else{
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page",page));
        }
    }

    public static void logoff(Context ctx){
        ctx.sessionAttribute("name", null);
        ctx.redirect("/");
    }
    // END
}
