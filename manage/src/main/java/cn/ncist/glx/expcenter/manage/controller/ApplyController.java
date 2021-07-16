package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.ApplyActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.PublicKey;

@RestController
@RequestMapping("apply")
public class ApplyController {
    @Resource
    ApplyActivityService applyActivityService;


    /**
     * 取消申请
     * @param id
     * @return
     */
    @GetMapping("del")
    public ResultVO del(int id){
        try {
            applyActivityService.del(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    /**
     * 教师的活动申请
     * @param tid
     * @return
     */
    @GetMapping("teacher/applies")
    public ResultVO getTeacher(int tid){
        return applyActivityService.getTeacher(tid);
    }

    /**
     * 教师负责人社团的活动
     * @param tid
     * @return
     */
    @GetMapping("teacher/club/applies")
    public ResultVO getLeader(int tid){
        return applyActivityService.getLeader(tid);
    }

    /**
     * 教师审核通过
     * @param id
     * @return
     */
    @GetMapping("/teacher/pass")
    public ResultVO teacherPass(int id){
        return applyActivityService.teacherPass(id,"1",null);
    }

    /**
     * 教师审核不通过
     * @param id
     * @return
     */
    @GetMapping("/teacher/unpass")
    public ResultVO teacherUnPass(int id,String reason){

        return applyActivityService.teacherPass(id,"3", reason);
    }


    /**
     * 社团查询自己发起的申请
     * @param cid
     * @return
     */
    @GetMapping("club/applies")
    public ResultVO getClub(int cid){
        return applyActivityService.getClub(cid);
    }

    /**
     * 管理员查看需要自己审核的活动申请
     * @return
     */
    @GetMapping("admin/applies")
    public ResultVO getAdmin(String type){
        return applyActivityService.getAdmin(type);
    }

    /**
     * 管理员审核通过
     * @param id
     * @return
     */
    @GetMapping("/admin/pass")
    public ResultVO adminPass(int id){
        return applyActivityService.teacherPass(id,"2",null);
    }

    /**
     * 管理审核不通过
     * @param id
     * @return
     */
    @GetMapping("/admin/unpass")
    public ResultVO adminUnPass(int id,String reason){

        return applyActivityService.teacherPass(id,"3", reason);
    }

    @GetMapping("getReason")
    public ResultVO getReason(int id){
        return applyActivityService.getReason(id);
    }

}
