package cn.ncist.glx.expcenter.beans.po;

import java.util.Date;

public class ApplyCourse {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.apply_time
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private Date applyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.status
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.course_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private Integer courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.teacher_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private Integer teacherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.type
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.desc
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private String desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_course.change_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    private Integer changeId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.id
     *
     * @return the value of apply_course.id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.id
     *
     * @param id the value for apply_course.id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.apply_time
     *
     * @return the value of apply_course.apply_time
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.apply_time
     *
     * @param applyTime the value for apply_course.apply_time
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.status
     *
     * @return the value of apply_course.status
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.status
     *
     * @param status the value for apply_course.status
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.course_id
     *
     * @return the value of apply_course.course_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.course_id
     *
     * @param courseId the value for apply_course.course_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.teacher_id
     *
     * @return the value of apply_course.teacher_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.teacher_id
     *
     * @param teacherId the value for apply_course.teacher_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.type
     *
     * @return the value of apply_course.type
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.type
     *
     * @param type the value for apply_course.type
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.desc
     *
     * @return the value of apply_course.desc
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.desc
     *
     * @param desc the value for apply_course.desc
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_course.change_id
     *
     * @return the value of apply_course.change_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public Integer getChangeId() {
        return changeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_course.change_id
     *
     * @param changeId the value for apply_course.change_id
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }
}