package cn.ncist.glx.expcenter.beans.vo;

/**
 * 一个用于显示课表的类，表示一天中某个课程节次时每个教室的课程
 */
public class DayNumberCourse {

    private String number;

    private String s901;
    private String s902;
    private String s903;
    private String s904;
    private String s905;
    private String s906;

    public DayNumberCourse(String number){
        this.number = number;

    }

    public DayNumberCourse(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getS901() {
        return s901;
    }

    public void setS901(String s901) {
        this.s901 = s901;
    }

    public String getS902() {
        return s902;
    }

    public void setS902(String s902) {
        this.s902 = s902;
    }

    public String getS903() {
        return s903;
    }

    public void setS903(String s903) {
        this.s903 = s903;
    }

    public String getS904() {
        return s904;
    }

    public void setS904(String s904) {
        this.s904 = s904;
    }

    public String getS905() {
        return s905;
    }

    public void setS905(String s905) {
        this.s905 = s905;
    }

    public String getS906() {
        return s906;
    }

    public void setS906(String s906) {
        this.s906 = s906;
    }

    @Override
    public String toString() {
        return "TodayCourse{" +
                "s901='" + s901 + '\'' +
                ", s902='" + s902 + '\'' +
                ", s903='" + s903 + '\'' +
                ", s904='" + s904 + '\'' +
                ", s905='" + s905 + '\'' +
                ", s906='" + s906 + '\'' +
                '}';
    }
}
