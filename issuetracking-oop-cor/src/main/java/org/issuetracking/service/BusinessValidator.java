/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service;

import org.issuetracking.model.Comment;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Project;
import org.issuetracking.model.User;

/**
 *
 * @author peta
 */
public class BusinessValidator {

    public static void validate(User user) throws ValidationException {
        Validator.isBetween(user.getNick(), 4, 20, "Nickname");
        Validator.isBetween(user.getPass(), 4, 20, "Password");
        Validator.isBetween(user.getEmail(), 8, 32, "Email");
    }

    public static void validate(Project project) throws ValidationException {
        Validator.isBetween(project.getName(), 4, 40, "Name");
        Validator.isBetween(project.getDescription(), 10, 100, "Description");
        Validator.isNotNull(project.getAuthor(), "Author");
    }

    public static void validate(Issue issue) throws ValidationException {
        Validator.isNotNull(issue.getProject(), "project");
        Validator.isNotNull(issue.getAssignee(), "assignee");
        Validator.isNotNull(issue.getReporter(), "reporter");
        Validator.isBetween(issue.getName(), 4, 40, "Name");
        Validator.isBetween(issue.getDescription(), 10, 200, "Description");
        Validator.isNotNull(issue.getPriority(), "priority");

    }

    public static void validate(Comment comment) throws ValidationException {
        Validator.isNotNull(comment.getIssue(), "issue");
        Validator.isNotNull(comment.getAuthor(), "author");
        Validator.isBetween(comment.getName(), 4, 40, "Name");
        Validator.isBetween(comment.getComment(), 10, 200, "Comment");

    }
}
