package com.project.myschedule;

/**
 * Created by sushil on 12/24/15.
 */
public class DataObject {
    private String scheduleTitle;
    private String toDate;
    private  String fromDate;

    DataObject (String scheduleTitle, String toDate, String fromDate){
        this.scheduleTitle = scheduleTitle;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public String getScheduleTitle() {return scheduleTitle;}

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate(){return fromDate;}

    public void  setFromDate(String fromDate){this.fromDate = fromDate;}



}
