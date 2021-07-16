package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.beans.vo.User;
import cn.ncist.glx.expcenter.manage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("hello")
    @ResponseBody
    public String Hello(){
        return "你好";
    }

    @Resource
    UserService service;


    @PostMapping("login")
    @ResponseBody
    public ResultVO login(@RequestBody User user, HttpServletRequest request){

       return service.login(user,request);
    }




}
