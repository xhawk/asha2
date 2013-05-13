package models;

import play.data.validation.Constraints;

/**
 *
 */
public class CaseType {

    @Constraints.Required
    private String name;
}
