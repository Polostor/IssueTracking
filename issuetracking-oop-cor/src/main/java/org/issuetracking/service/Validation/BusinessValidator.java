/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service.Validation;

import org.issuetracking.model.Comment;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Project;
import org.issuetracking.model.Status;
import org.issuetracking.model.User;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.iface.PrincipalBeanInterface;

/**
 *
 * @author peta
 */
public class BusinessValidator {

    public static void validate(Object obj, PrincipalBeanInterface pb) throws ValidationException {
        if (User.class.isInstance(obj)) validateUser((User) obj, pb);
        if (Project.class.isInstance(obj)) validateProject((Project) obj, pb);
        if (Issue.class.isInstance(obj)) validateIssue((Issue) obj, pb);
        if (Comment.class.isInstance(obj)) validateComment((Comment) obj, pb);
    }

    public static void validate(Object obj, PrincipalBeanInterface pb, Object obj2) throws ValidationException {
        if (Issue.class.isInstance(obj)){
            if (Status.class.isInstance(obj2)){
                Validator.isNotSame( ((Issue)obj) .getStatus(), (Status) obj2, "Status");
            }
            if (Priority.class.isInstance(obj2)){
                Validator.isNotSame(((Issue)obj) .getPriority(), (Priority) obj2, "Priority");
            }
            validateIssue((Issue) obj, pb);
        }
    }

    private static void validateComment(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        ObjectValidator.validate(comment);
        Validator.isLoggedIn(pb);
        Validator.isOneOfAllowedUsers(comment.getIssue().getAssignee(), comment.getIssue().getReporter(), pb);
    }

    private static void validateIssue(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        ObjectValidator.validate(issue);
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(issue.getAssignee(), pb);
    }

    private static void validateProject(Project project, PrincipalBeanInterface pb) throws ValidationException {
        ObjectValidator.validate(project);
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(project.getAuthor(), pb);
    }

    private static void validateUser(User user, PrincipalBeanInterface pb) throws ValidationException {
        ObjectValidator.validate(user);
        if (user.getId() == null) {
            Validator.isNotLoggedIn(pb);
            return;
        }
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(user, pb);
    }

}
