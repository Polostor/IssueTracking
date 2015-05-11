package org.issuetracking.view;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

@Named
@RequestScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String welcome = "Welcome at the IssueTracking webpage";

    public String getWelcome() {
        return welcome;
    }

    public Priority[] getPriorities(){
        return Priority.values();
    }

    public Status[] getStatuses(){
        return Status.values();
    }
}
