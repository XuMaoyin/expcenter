package cn.ncist.glx.expcenter.home.mapper;

import cn.ncist.glx.expcenter.beans.po.Classes;
import java.util.List;

public interface ClassesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classes
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classes
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int insert(Classes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classes
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    Classes selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classes
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    List<Classes> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classes
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int updateByPrimaryKey(Classes record);
}