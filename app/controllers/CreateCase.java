package controllers;

import models.Case;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.createCase;

/**
 * Controller for creating a new Case
 */
@Security.Authenticated(Secured.class)
public class CreateCase extends Controller {
    static Form<Case> caseForm = Form.form(Case.class);

    public static Result createCase() {
        Form<Case> filledForm = caseForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            System.out.println("Form has errors");
            return badRequest(
                    views.html.cases.render(Case.all(), User.find.byId(request().username()))
            );
        } else {
            System.out.println("Saving case");
            Case.create(filledForm.get());
            return redirect(routes.CreateCase.index());
        }
    }

    /**
     * Render page
     * @return
     */
    public static Result index() {
        return ok(createCase.render(caseForm, User.find.byId(request().username())));
    }
}
