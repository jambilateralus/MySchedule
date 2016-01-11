package com.project.myschedule;

/**
 * Created by cruzor on 1/11/16.
 */
public class TaskDataObject {

    private String taskName;
    private String description;
    //private  String fromDate;
    private boolean notification;
    private  String  startEndTime;
    private String icon;
    private String priorityLevel;

    TaskDataObject (String taskName, String description, boolean notification, String  startEndTime, String icon , String priorityLevel, long scheduleId){
        this.taskName = taskName;
        this.description = description;
        this.notification = notification;
        this.startEndTime = startEndTime;
        this.icon = icon;
        this.priorityLevel = priorityLevel;

    }

    public String getTaskName() {return taskName;}

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description= description;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getStartEndTime(){return startEndTime;}

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }


    public String getIcon(){return icon;}

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getPriorityLevel(){return priorityLevel;}

    public void setPriorityLevel(String priorityLevel){
        this.priorityLevel = priorityLevel;
    }

}
