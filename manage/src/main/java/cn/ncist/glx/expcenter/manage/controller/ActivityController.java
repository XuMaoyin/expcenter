package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Activity;
import cn.ncist.glx.expcenter.beans.po.ApplyActivity;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.beans.vo.User;
import cn.ncist.glx.expcenter.manage.service.ActivityService;
import cn.ncist.glx.expcenter.manage.service.ApplyActivityService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Resource
    ActivityService activityService;

    @Resource
    ApplyActivityService applyActivityService;


    /**
     * 添加活动记录
     * @param activity
     * @return
     */
    @PostMapping("add")
    public ResultVO add(@RequestBody Activity activity, HttpServletRequest request){
//先执行活动添加
        activity.setDatetime(DateUtil.stringToDate(activity.getDate()));
        ResultVO resultVO = activityService.add(activity);

        User user =(User) request.getSession().getAttribute("user");
        String identity = user.getIdentity();
        //非管理员添加要进行审核
        if(identity != "admin"){
            ApplyActivity applyActivity = new ApplyActivity();
            applyActivity.setActivityId(activity.getId());

            if(identity == "teacher"){
                applyActivity.setStatus("1");
            }else{
                applyActivity.setStatus("0");
            }
            resultVO =  applyActivityService.add(applyActivity);
        }
        return resultVO;
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @GetMapping("del")
    public ResultVO del(int id){
        return activityService.del(id);
    }

    /**
     * 修改活动内容
     * @param activity
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody Activity activity,HttpServletRequest request){

        //修改活动信息则重新发起申请
        User user =(User) request.getSession().getAttribute("user");
        String identity = user.getIdentity();
        if(identity != "admin") {
            ApplyActivity applyActivity = new ApplyActivity();
            applyActivity.setActivityId(activity.getId());

            if (identity == "teacher") {
                applyActivity.setStatus("1");
            } else {
                applyActivity.setStatus("0");
            }
           applyActivityService.add(applyActivity);
        }

        return activityService.update(activity);
    }

    /**
     *查询该类型（teacher或者club的所有活动
     * @param type
     * @return
     */
    @GetMapping("activities")
    public ResultVO getAll(String type){
        return activityService.getAll(type);
    }

    /**
     *查询该类型（teacher或者club的所有活动
     * @param tid
     * @return
     */
    @GetMapping("teacher")
    public ResultVO getTeacher(int  tid){
        return activityService.getTeacher(tid);
    }

    @GetMapping("club")
    public ResultVO getClub(int  cid){
        return activityService.getClub(cid);
    }

    /**
     * 根据id查询一条活动记录
     * @param id
     * @return
     */
    @GetMapping("activity")
    public ResultVO getOne(int id){
        return activityService.getOne(id);
    }

    /**
     * 根据活动名称和类型搜索
     * @param type
     * @param activityName
     * @return
     */
    @GetMapping("search")
    public ResultVO search(String type,String activityName){
        return activityService.getByTypeAndName(activityName,type);
    }


}
