package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Activity;
import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ActivityMapper;
import cn.ncist.glx.expcenter.manage.mapper.ClassroomMapper;
import cn.ncist.glx.expcenter.manage.mapper.ClubMapper;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityService {

    @Resource
    ActivityMapper activityMapper;

    @Resource
    ClassroomMapper classroomMapper ;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ClubMapper clubMapper;

    /**
     * 添加活动
     *
     * @param activity
     * @return
     */
    public ResultVO add(Activity activity) {
        try {
            int insert = activityMapper.insert(activity);
            return ResultVO.getSuccess("添加成功",insert);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    public ResultVO del(int id) {
        try {
            activityMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    /**
     * 修改活动信息
     * @param activity
     * @return
     */
    public ResultVO update(Activity activity){
        try {
            activityMapper.updateByPrimaryKey(activity);
            return ResultVO.getSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，修改失败");
        }
    }

    /**
     * 根据类型查询所有
     *
     * @param type
     * @return
     */
    public ResultVO getAll(String type) {
        List<Activity> list = activityMapper.selectAll(type);
        for (Activity activity:list){
            setActivity(activity);
        }
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 根据id查询一条记录
     *
     * @param id
     * @return
     */
    public ResultVO getOne(int id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        setActivity(activity);
        return ResultVO.getSuccess("查询成功", activity);
    }

    /**
     * 根据活动名称搜索
     * @param activityName
     * @param type
     * @return
     */
    public ResultVO getByTypeAndName(String activityName,String type){
         List<Activity> list = activityMapper.selectByName(activityName,type);
         for (Activity activity:list){
             setActivity(activity);
         }
         return ResultVO.getSuccess("查询成功",list);
    }

    /**
     * 完善活动信息
     * @param activity
     */
    public void setActivity(Activity activity){
        //设置用于显示日期
        activity.setDate(DateUtil.DateToString(activity.getDatetime()));
        //设置教师
        activity.setTeacher(teacherMapper.selectByPrimaryKey(activity.getLeaderId()));
        //设置教室
        activity.setClassroom(classroomMapper.selectByPrimaryKey(activity.getRoomId()));
        //设置社团信息
        if(activity.getClubId() != null){
            activity.setClub(clubMapper.selectByPrimaryKey(activity.getClubId()));
        }
    }

    /**
     * 查询某个申请教师的活动
     * @param tid
     * @return
     */
    public ResultVO getTeacher(int tid) {
        List<Activity> list = activityMapper.geTeacher(tid);
        for(Activity activity:list){
            setActivity(activity);
        }

        return ResultVO.getSuccess("查询成功",list);
    }

    /**
     * 查询某个社团的活动
     * @param cid
     * @return
     */
    public ResultVO getClub(int cid) {
        List<Activity> list = activityMapper.getClub(cid);
        for(Activity activity:list){
            setActivity(activity);
        }

        return ResultVO.getSuccess("查询成功",list);
    }


}
