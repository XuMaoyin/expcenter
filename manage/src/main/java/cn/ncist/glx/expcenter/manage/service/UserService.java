package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Club;
import cn.ncist.glx.expcenter.beans.po.Config;
import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.beans.vo.User;
import cn.ncist.glx.expcenter.manage.mapper.ClubMapper;
import cn.ncist.glx.expcenter.manage.mapper.ConfigMapper;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Resource
    ConfigMapper adminMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    ClubMapper clubMapper;


    public ResultVO login(User user, HttpServletRequest request) {
        try{
            boolean flag = false ;

            switch (user.getIdentity()){
                case "admin":
                    Config config = adminMapper.selectByPrimaryKey("password");
                    String password = config.getValue();
                    if(user.getPassword().equals(password)) {
                        request.getSession().setAttribute("user", user);
                        return ResultVO.getSuccess("登录成功", user);
                    }
                    break;
                case "teacher":
                    Teacher teacher = teacherMapper.selectByUsername(user.getUsername());
                    //System.out.println(teacher);
                    //System.out.println(user);
                    if(teacher == null)
                        return ResultVO .getFail("登录失败，用户名不存在");

                    if(!teacher.getPassword().equals(user.getPassword()))
                        return ResultVO.getFail("登录失败，请检查您的用户名及密码是否匹配，并选择合适的身份进行登录！");

                    request.getSession().setAttribute("user", user);
                    return ResultVO.getSuccess("登录成功",teacher);

                case "student":
                    Club club = clubMapper .login(user.getUsername(),user.getPassword());
                    if(null != club){
                        request.getSession().setAttribute("user", user);
                        return ResultVO.getSuccess("登录成功",club);
                    }
                    break;

            }
            return ResultVO.getFail("登录失败，请检查您的用户名及密码是否匹配，并选择合适的身份进行登录！");
        }catch (Exception e){
            e.printStackTrace();

            return ResultVO.getFail("系统错误");
        }




    }

}


