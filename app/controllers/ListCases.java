package controllers;

import models.Case;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.cases;

import java.util.List;

/**
 * Returns a list of all cases
 */
@Security.Authenticated(Secured.class)
public class ListCases extends Controller {

    public static Result getCases() {
        List<Case> caseList = Case.all();
        System.out.println(caseList.size());

        return ok(cases.render(caseList, User.find.byId(request().username())));
    }
}
