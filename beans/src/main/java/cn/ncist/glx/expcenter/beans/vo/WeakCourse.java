package cn.ncist.glx.expcenter.beans.vo;

import java.util.ArrayList;
import java.util.List;
/**
 * 一个用于显示课表的类，表示一周每天的课程
 */
public class WeakCourse {


    private WeakNumberCourse s1;
    private WeakNumberCourse s2;
    private WeakNumberCourse s3;
    private WeakNumberCourse s4;
    private WeakNumberCourse s5;

    private List<WeakNumberCourse> list = new ArrayList<>();

    public WeakCourse(){
        this.s1 = new WeakNumberCourse("1-2节");
        this.s2 = new WeakNumberCourse("3-4节");
        this.s3 = new WeakNumberCourse("5-6节");
        this.s4 = new WeakNumberCourse("7-8节");
        this.s5 = new WeakNumberCourse("9-10节");
    }
    
    public List<WeakNumberCourse> getList() {
        return list;
    }

    public void setList() {
        listAdd(s1);
        listAdd(s2);
        listAdd(s3);
        listAdd(s4);
        listAdd(s5);

    }

    public void listAdd(WeakNumberCourse WeakNumberCourse){
        if(WeakNumberCourse != null){
            list.add(WeakNumberCourse);
        }
    }

    public WeakNumberCourse getS1() {
        return s1;
    }

    public void setS1(WeakNumberCourse s1) {
        this.s1 = s1;
    }

    public WeakNumberCourse getS2() {
        return s2;
    }

    public void setS2(WeakNumberCourse s2) {
        this.s2 = s2;
    }

    public WeakNumberCourse getS3() {
        return s3;
    }

    public void setS3(WeakNumberCourse s3) {
        this.s3 = s3;
    }

    public WeakNumberCourse getS4() {
        return s4;
    }

    public void setS4(WeakNumberCourse s4) {
        this.s4 = s4;
    }

    public WeakNumberCourse getS5() {
        return s5;
    }

    public void setS5(WeakNumberCourse s5) {
        this.s5 = s5;
    }
}
