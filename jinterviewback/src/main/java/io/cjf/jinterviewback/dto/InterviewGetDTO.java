package io.cjf.jinterviewback.dto;

import io.cjf.jinterviewback.po.Interview;
import io.cjf.jinterviewback.po.User;

public class InterviewGetDTO extends Interview {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
