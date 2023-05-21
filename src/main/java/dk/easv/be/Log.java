package dk.easv.be;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private String logAction;
    private LocalDateTime actionTime;
    private int user_id;

    public Log(int id, String logAction, LocalDateTime actionTime, int user_id) {
        this.id = id;
        this.logAction = logAction;
        this.actionTime = actionTime;
        this.user_id = user_id;
    }

    public Log(String logAction, LocalDateTime actionTime, int user_id) {
        this.logAction = logAction;
        this.actionTime = actionTime;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "[" +actionTime +"]" +" /" +logAction +" /By user:" +user_id;
    }
}
