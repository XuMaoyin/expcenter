package cn.ncist.glx.expcenter.manage.mapper;

import cn.ncist.glx.expcenter.beans.po.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Activity record);


    Activity selectByPrimaryKey(Integer id);

    List<Activity> selectAll(String type);


    int updateByPrimaryKey(Activity record);

    List<Activity> selectByName(@Param("activityName") String activityName, @Param("type") String type);

    int getRoomCount(int id);

    List<Activity> geTeacher(int tid);

    List<Activity> getClub(int cid);
}