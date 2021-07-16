package cn.ncist.glx.expcenter.beans.po;

import java.util.List;

public class Course {

    private Integer id;


    private String name;


    private String counts;


    private String people;


    private Integer leaderId;

    private String classIds;

    private Teacher teacher;
//课程对应的实验对象
    private List<Experiment> experiment;
//解析后的班级名称
    private String classes ;
//解析出的班级列表
    private List<Classes> classesList;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public List<Experiment> getExperiment() {
        return experiment;
    }

    public void setExperiment(List<Experiment> experiment) {
        this.experiment = experiment;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Classes> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Classes> classesList) {
        this.classesList = classesList;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCounts() {
        return counts;
    }


    public void setCounts(String counts) {
        this.counts = counts;
    }


    public String getPeople() {
        return people;
    }


    public void setPeople(String people) {
        this.people = people;
    }


    public Integer getLeaderId() {
        return leaderId;
    }


    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }


    public String getClassIds() {
        return classIds;
    }


    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", counts='" + counts + '\'' +
                ", people='" + people + '\'' +
                ", leaderId=" + leaderId +
                ", classIds='" + classIds + '\'' +
                ", teacher=" + teacher.getNickname() +
                ", experiment=" + experiment +
                ", classesList=" + classesList +
                '}';
    }
}