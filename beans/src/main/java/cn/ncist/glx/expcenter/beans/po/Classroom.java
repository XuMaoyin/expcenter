package cn.ncist.glx.expcenter.beans.po;

public class Classroom {

    private Integer id;


    private String roomName;


    private String number;


    private String desc;


    private Integer leaderId;


    private String people;
//本期实验室使用次数
    private int count ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getRoomName() {
        return roomName;
    }


    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Integer getLeaderId() {
        return leaderId;
    }


    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }


    public String getPeople() {
        return people;
    }


    public void setPeople(String people) {
        this.people = people;
    }
}