package controllers;

import models.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.mvc.*;

import play.mvc.Controller;

import java.util.List;

import static play.data.Form.form;

@org.springframework.stereotype.Controller
@Security.Authenticated(Secured.class)
public class Application extends Controller {

    @Autowired
    public ProcessEngine processEngine;
  
    public Result index() {
        List<ProcessDefinition> pdl = processEngine.getRepositoryService().
                createProcessDefinitionQuery().list();
        return ok(views.html.index.render(pdl));
    }

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

    /**
     * Login page.
     */
    public static Result login() {
        return ok(views.html.login.render(form(Login.class)));
    }

    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }
}
