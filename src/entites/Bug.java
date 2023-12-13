package entites;

import Users.Tester;
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
    private User developer;
    private User tester;
    private String screenshotPath;


    public Bug(Integer id,
               String bugName,
               String bugType,
               String priority,
               String bugLevel,
               String projectName,
               String bugDate,
               BugStatus status,
               User developer,
               User tester) {
        this.id = id;
        this.bugName = bugName;
        this.bugType = bugType;
        this.priority = priority;
        this.bugLevel = bugLevel;
        this.projectName = projectName;
        this.bugDate = bugDate;
        this.status = status;
        this.developer = developer;
        this.tester = tester;
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

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public User getTester() {
        return tester;
    }

    public void setTester(User tester) {
        this.tester = tester;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }
}
