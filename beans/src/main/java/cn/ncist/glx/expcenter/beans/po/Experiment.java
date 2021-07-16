package cn.ncist.glx.expcenter.beans.po;

import java.util.Date;

public class Experiment {
    private Integer id;

    private Integer courseId;

    private String name;

    private Integer roomId;

    private String desc;

    private Date datetime;

    private Integer number;

    private Course course ;

    private Classroom classroom ;

    //用于显示的实验日期
    private String date;
    //用于显示的实验日期对应的周数和周次
    private String weak;
    //是否可编辑，用于前端操作
    private boolean edit =false;

    public String getWeak() {
        return weak;
    }

    public void setWeak(String weak) {
        this.weak = weak;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", roomId=" + roomId +
                ", desc='" + desc + '\'' +
                ", datetime=" + datetime +
                ", number=" + number +
                ", course=" + course +
                ", classroom=" + classroom +
                ", date='" + date + '\'' +
                ", weak='" + weak + '\'' +
                ", edit=" + edit +
                '}';
    }
}