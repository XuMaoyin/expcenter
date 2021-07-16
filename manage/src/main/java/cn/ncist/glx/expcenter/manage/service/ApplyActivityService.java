package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Activity;
import cn.ncist.glx.expcenter.beans.po.ApplyActivity;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplyActivityService {

    @Resource
    ApplyActivityMapper applyActivityMapper;

    @Resource
    ActivityMapper activityMapper;

    @Resource
    ClassroomMapper classroomMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ClubMapper clubMapper;

    /**
     * 添加活动申请
     * @param applyActivity
     * @return
     */
    public ResultVO add(ApplyActivity applyActivity) {
        try {
            applyActivityMapper.insert(applyActivity);
            return ResultVO.getSuccess("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }
    }

    /**
     * 删除活动申请
     * @param id
     * @return
     */
    public ResultVO del(int id) {
        try {
            //删除申请时把活动的状态设置为0，，也就是待申请状态
            ApplyActivity applyActivity = applyActivityMapper.selectByPrimaryKey(id);
            Activity activity = activityMapper.selectByPrimaryKey(applyActivity.getActivityId());
            activity.setStatus("0");
            activityMapper.updateByPrimaryKey(activity);
            //执行删除
            applyActivityMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    /**
     * 修改活动申请
     * @param applyActivity
     * @return
     */
    public ResultVO update(ApplyActivity applyActivity) {
        try {
            applyActivityMapper.updateByPrimaryKey(applyActivity);
            return ResultVO.getSuccess("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFail("系统错误，修改失败");
        }
    }

    /**
     * 查询所有申请
     * @return
     */
    public ResultVO getAll() {
        List<ApplyActivity> list = applyActivityMapper.selectAll();
        setActivity(list);
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 查询某个教师的活动申请
     * @param tid
     * @return
     */
    public ResultVO getTeacher(int tid) {
        List<ApplyActivity> list = applyActivityMapper.selectTeacher(tid);
        setActivity(list);
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 查询某个社团的活动申请
     * @param cid
     * @return
     */
    public ResultVO getClub(int cid) {
        List<ApplyActivity> list = applyActivityMapper.selectClub(cid);
        setActivity(list);
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 查询某个教师作为指导老师的社团活动申请（待指导老师审核）
     * @param tid
     * @return
     */
    public ResultVO getLeader(int tid) {
        List<ApplyActivity> list = applyActivityMapper.selectLeader(tid);
        setActivity(list);
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 完善活动信息
     *
     * @param list
     */
    public void setActivity(List<ApplyActivity> list) {
        for (ApplyActivity applyActivity : list) {
            //设置用于显示日期
            applyActivity.setDate(DateUtil.DateToString(applyActivity.getDatetime()));

            //设置活动信息
            Activity activity = applyActivity.getActivity();

            activity.setDate(DateUtil.DateToString(activity.getDatetime()));
            //设置教师
            activity.setTeacher(teacherMapper.selectByPrimaryKey(activity.getLeaderId()));
            //设置教室
            activity.setClassroom(classroomMapper.selectByPrimaryKey(activity.getRoomId()));
            //设置社团信息
            if (activity.getClubId() != null) {
                activity.setClub(clubMapper.selectByPrimaryKey(activity.getClubId()));
            }
        }

    }


    /**
     * 教师审核,通过设置status为1，不通过设置为3
     * @param id
     * @return
     */
    public ResultVO teacherPass(int id,String status,String reason) {

        ApplyActivity applyActivity = applyActivityMapper.selectByPrimaryKey(id);
        applyActivity.setStatus(status);
        applyActivity.setReason(reason);

        //成功或者失败的话同步更新活动状态
        if(status.equals("3") || status.equals("2")){
            Activity activity = activityMapper.selectByPrimaryKey(applyActivity.getActivityId());
            activity.setStatus(status);
            activityMapper.updateByPrimaryKey(activity);
        }


        return update(applyActivity);
    }

    /**
     * 获取需要管理员审核的活动
     * @param type 类型为空时查询全部
     * @return
     */
    public ResultVO getAdmin(String type){
        List<ApplyActivity> list = null ;
        if(type == null || type == "null" ){
            list =  applyActivityMapper.selectAdmin();
        }else {
            list =  applyActivityMapper.selectAdminType(type);
        }
        //System.out.println(type);
        //System.out.println(list);
        setActivity(list);

       return ResultVO.getSuccess("查询成功",list);
    }


    public ResultVO getReason(int id) {

        return ResultVO.getSuccess("查询成功",applyActivityMapper.getReason(id));

    }
}
