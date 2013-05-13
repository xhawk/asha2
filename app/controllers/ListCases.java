package controllers;

import models.Case;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cases;

import java.util.List;

/**
 * Returns a list of all cases
 */
public class ListCases extends Controller {

    public static Result getCases() {
        List<Case> caseList = Case.all();
        System.out.println(caseList.size());

        return ok(cases.render(caseList));
    }
}
