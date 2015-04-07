package org.issuetracking.service.generic;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

public interface GenericCommentServiceInterface extends GenericSpecificServiceInterface<Comment, CommentDAO> {
}
