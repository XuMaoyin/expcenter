package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Course;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("course")
public class CourseController {

    @Resource
    CourseService courseService;

    /**
     * 查询所有课程
     * @return
     */
    @GetMapping("courses")
    public ResultVO getAll(){

        return courseService.getAll();
    }

    /**
     * 查询某个教师的课程
     * @param tid
     * @return
     */
    @GetMapping("courses/teacher")
    public ResultVO getByTeacher(int tid){
        return courseService.getByTeacherId(tid);
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @PostMapping("add")
    public ResultVO add(@RequestBody Course course){
        return courseService.add(course);
    }

    /**
     * 导入课程
     * @param courses
     * @return
     */
    @PostMapping("import")
    public ResultVO imp(@RequestBody Course[] courses){
        //System.out.println(Arrays.asList(courses));
        return courseService.imp(courses);

    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody Course course){
        return courseService.update(course);
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @GetMapping("del")
    public ResultVO del( int  id){
        return courseService.del(id);
    }


    @GetMapping("course")
    public ResultVO getOne(int id){
        return courseService.getOne(id);
    }



}
