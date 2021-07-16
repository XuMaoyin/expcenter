package cn.ncist.glx.expcenter.manage.mapper;

import cn.ncist.glx.expcenter.beans.po.Notice;

import java.util.List;

public interface NoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int insert(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    Notice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    List<Notice> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Thu Apr 29 17:13:43 CST 2021
     */
    int updateByPrimaryKey(Notice record);

    void doTop(int id);

    List<Notice> selectByTitle(String title);

    void unTop(int id);
}