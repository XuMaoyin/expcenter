package cn.ncist.glx.expcenter.home.mapper;

import cn.ncist.glx.expcenter.beans.po.ApplyCourse;
import java.util.List;

public interface ApplyCourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_course
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_course
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int insert(ApplyCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_course
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    ApplyCourse selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_course
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    List<ApplyCourse> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apply_course
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int updateByPrimaryKey(ApplyCourse record);
}