package cn.ncist.glx.expcenter.beans.po;

import java.util.List;

public class Teacher {

    private Integer id;


    private String username;


    private String password;


    private String nickname;


    private String sex;


    private String birthday;


    private String desc;


    private String email;


    private String tel;


    private String department;

    //教师当前学期任教的课程
    private List<Course> course;
    //教师当前学期任教的课程数
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getBirthday() {
        return birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel() {
        return tel;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", desc='" + desc + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}