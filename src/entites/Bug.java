package entites;

import enums.BugStatus;

import java.util.Arrays;

public class Bug {
    private Integer id;
    private String bugName;
    private String bugType;
    private String priority;
    private String bugLevel;
    private String projectName;
    private String bugDate;
    private BugStatus status;
    private User[] developers;


    public Bug(Integer id,
               String bugName,
               String bugType,
               String priority,
               String bugLevel,
               String projectName,
               String bugDate,
               BugStatus status,
               User[] developers) {
        this.id = id;
        this.bugName = bugName;
        this.bugType = bugType;
        this.priority = priority;
        this.bugLevel = bugLevel;
        this.projectName = projectName;
        this.bugDate = bugDate;
        this.status = status;
        this.developers = developers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugName() {
        return bugName;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBugDate() {
        return bugDate;
    }

    public void setBugDate(String bugDate) {
        this.bugDate = bugDate;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public User[] getDevelopers() {
        return developers;
    }

    public void setDevelopers(User[] developers) {
        this.developers = developers;
    }
}
