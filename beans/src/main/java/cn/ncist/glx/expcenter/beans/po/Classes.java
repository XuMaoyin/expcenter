package cn.ncist.glx.expcenter.beans.po;

import java.util.List;

public class Classes {

    private Integer id;


    private String className;


    private String people;


    private String desc;


    private Integer leaderId;

    private  String leader ;
//班级负责教师
    private Teacher teacher ;
//班级对应的课程
    private List<Course> courses ;
//班级本期课程数
    private int count ;

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

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


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public String getPeople() {
        return people;
    }


    public void setPeople(String people) {
        this.people = people;
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

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", people='" + people + '\'' +
                '}';
    }
}