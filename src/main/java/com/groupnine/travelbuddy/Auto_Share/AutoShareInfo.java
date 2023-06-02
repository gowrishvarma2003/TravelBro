package com.groupnine.travelbuddy.Auto_Share;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class AutoShareInfo {.
    String fullname, email, mobile, place;
    Integer no_of_vacs;
    Time time;
    Date date;
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }
    public Integer getNo_of_vacs() { return no_of_vacs; }
    public void setNo_of_vacs(Integer no_of_vacs) { this.no_of_vacs = no_of_vacs; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getDateInString() {
        return date.toString();
    }
    public Time getTime() { return time; }
    public String getTimeInString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String timeStr = format.format(time);
        return timeStr;
    }
    public void setTime(Time time) { this.time = time; }
    public AutoShareInfo(String fullname, String email, String mobile, String place, Integer no_of_vacs, Date date, Time time) {
        setFullname(fullname); setEmail(email); setMobile(mobile); setPlace(place); setNo_of_vacs(no_of_vacs); setDate(date); setTime(time);
    }
}
