package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.utils.UsernameUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.beans.vo.User;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import cn.ncist.glx.expcenter.manage.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService ;

    /**
     * 教师本人信息更新
     * @param teacher 前端传来的修改后的信息
     * @param request
     * @return
     */
    @PostMapping("updateInfo")
    public ResultVO updateInfo(@RequestBody Teacher teacher, HttpServletRequest request){
        //System.out.println(teacher);
        //调用service层进行处理
        ResultVO result = teacherService.updateInfo(teacher);

        //修改成功则修改session中对应的登录信息
        if(result.getCode() == 200){
            HttpSession session = request.getSession();
            User user =(User) session.getAttribute("user");
            user.setUsername(teacher.getUsername());
            user.setPassword(teacher.getPassword());
            session.setAttribute("user",user);
        }

        return result ;
    }

    /**
     * 查询所有教师信息
     * @return
     */
    @RequestMapping("teachers")
    public ResultVO getAll(){
        return teacherService.getAll();
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    @PostMapping("add")
    public ResultVO add(@RequestBody Teacher teacher){
        teacher.setUsername(UsernameUtil.createUsername());
       return teacherService.add(teacher);
    }

    @PostMapping("import")
    public ResultVO imp(@RequestBody Teacher[] arr){
        System.out.println(arr[0]);
        ResultVO resultVO =null ;
        for(Teacher teacher:arr){
            resultVO = teacherService.add(teacher);
        }
        return resultVO;
    }

    /**
     * 根据教师id查询单个教师信息
     * @param id
     * @return
     */
    @GetMapping("teacher")
    public ResultVO getById(int id){
        return teacherService.getById(id);
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody Teacher teacher){
        return teacherService.updateInfo(teacher);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @GetMapping("del")
    public ResultVO del(int id){
        return teacherService.del(id);
    }


}
