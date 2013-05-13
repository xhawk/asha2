package controllers;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.*;

import play.mvc.Controller;

import java.util.List;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    public ProcessEngine processEngine;
  
    public Result index() {
        List<ProcessDefinition> pdl = processEngine.getRepositoryService().
                createProcessDefinitionQuery().list();
        return ok(views.html.index.render(pdl));
    }
  
}
