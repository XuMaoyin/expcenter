package cn.ncist.glx.expcenter.beans.vo;
/**
 * 一个用于显示课表的类，表示一周每天某个课时的课程
 */
public class WeakNumberCourse {

    private String number;

    private String monday="";
    private String tuesday="";
    private String wednesday="";
    private String thursday="";
    private String friday="";
    private String saturday="";
    private String sunday="";

    public WeakNumberCourse(String number){
        this.number = number;
    }

    public WeakNumberCourse(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday += monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday += tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday += wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday += thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday += friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday += saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday += sunday;
    }
}
