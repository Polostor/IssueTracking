package org.issuetracking.view;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

@ApplicationScoped	
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
