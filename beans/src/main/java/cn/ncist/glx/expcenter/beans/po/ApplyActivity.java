package cn.ncist.glx.expcenter.beans.po;

import java.util.Date;

public class ApplyActivity {

    private Integer id;


    private Date datetime;


    private String status;

    private String reason ;

    private Integer activityId;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private Activity activity ;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getDatetime() {
        return datetime;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getActivityId() {
        return activityId;
    }


    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ApplyActivity{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", status='" + status + '\'' +
                ", activityId=" + activityId +
                ", activity=" + activity +
                '}';
    }
}