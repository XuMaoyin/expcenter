package cn.ncist.glx.expcenter.beans.po;

import java.util.Date;

public class Activity {

    private Integer id;


    private String activityName;


    private Integer roomId;

    private Date datetime;

    private Integer number;


    private String details;


    private String people;


    private String status;


    private String type;


    private Integer clubId;


    private Integer leaderId;

    //社团信息
    private Club club;
    //教师信息
    private Teacher teacher ;
    //教室信息
    private Classroom classroom ;
    //显示日期
    private String date;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getActivityName() {
        return activityName;
    }


    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public Integer getRoomId() {
        return roomId;
    }


    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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


    public String getDetails() {
        return details;
    }


    public void setDetails(String details) {
        this.details = details;
    }


    public String getPeople() {
        return people;
    }


    public void setPeople(String people) {
        this.people = people;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Integer getClubId() {
        return clubId;
    }


    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }


    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", roomId=" + roomId +
                ", datetime=" + datetime +
                ", number=" + number +
                ", details='" + details + '\'' +
                ", people='" + people + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", clubId=" + clubId +
                ", leaderId=" + leaderId +
                ", club=" + club +
                ", teacher=" + teacher +
                ", classroom=" + classroom +
                ", date='" + date + '\'' +
                '}';
    }
}