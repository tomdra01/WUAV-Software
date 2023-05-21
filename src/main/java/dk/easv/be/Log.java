package dk.easv.be;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private String logAction;
    private LocalDateTime actionTime;
    private String  username;

    public Log(int id, String logAction, LocalDateTime actionTime, String username) {
        this.id = id;
        this.logAction = logAction;
        this.actionTime = actionTime;
        this.username = username;
    }

    public Log(String logAction, LocalDateTime actionTime, String username) {
        this.logAction = logAction;
        this.actionTime = actionTime;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[" +actionTime +"]" +" /" +logAction +" /By user:" +username;
    }
}
