package cn.ncist.glx.expcenter.home.contraller;

import cn.ncist.glx.expcenter.beans.po.Course;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.home.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("course")
public class CourseController {


    @Resource
    CourseService courseService;

    @GetMapping("today")
    public ResultVO today(){
        return courseService.today();
    }

    @GetMapping("weak")
    public ResultVO weak(){
        return courseService.weak();
    }
}
