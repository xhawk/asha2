package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Case
 */
@Entity
public class Case extends Model {

    @Required
    public String name;

    @Id
    public Long id;

    public String caseType;
    public String tos;

    public static List<Case> all() {
        return find.all();
    }

    public static Finder find = new Finder(
            Long.class, Case.class
    );

    public static void create(Case aCase) {
        aCase.save();
    }

}
