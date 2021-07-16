package cn.ncist.glx.expcenter.beans.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个用于显示课表的类，表示一天每个教室的课程
 */
public class DayCourse {


    private DayNumberCourse s1;
    private DayNumberCourse s2;
    private DayNumberCourse s3;
    private DayNumberCourse s4;
    private DayNumberCourse s5;

    private List<DayNumberCourse> list = new ArrayList<>();;

    public DayCourse(){

    }

    public List<DayNumberCourse> getList() {
        return list;
    }

    public void setList() {
        listAdd(s1);
        listAdd(s2);
        listAdd(s3);
        listAdd(s4);
        listAdd(s5);

    }

    public void listAdd(DayNumberCourse dayNumberCourse){
        if(dayNumberCourse != null){
            list.add(dayNumberCourse);
        }
    }

    public DayNumberCourse getS1() {
        return s1;
    }

    public void setS1(DayNumberCourse s1) {
        this.s1 = s1;
    }

    public DayNumberCourse getS2() {
        return s2;
    }

    public void setS2(DayNumberCourse s2) {
        this.s2 = s2;
    }

    public DayNumberCourse getS3() {
        return s3;
    }

    public void setS3(DayNumberCourse s3) {
        this.s3 = s3;
    }

    public DayNumberCourse getS4() {
        return s4;
    }

    public void setS4(DayNumberCourse s4) {
        this.s4 = s4;
    }

    public DayNumberCourse getS5() {
        return s5;
    }

    public void setS5(DayNumberCourse s5) {
        this.s5 = s5;
    }
}
