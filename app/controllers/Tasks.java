package controllers;

import common.ProcessEngine;
import models.User;
import org.activiti.engine.repository.ProcessDefinition;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 *
 */
@Security.Authenticated(Secured.class)
public class Tasks extends Controller {

    public Result index() {
        List<ProcessDefinition> pdl = ProcessEngine.getInstance().getRepositoryService().
                createProcessDefinitionQuery().list();
        return ok(views.html.index.render(pdl, User.find.byId(request().username())));
    }
}
