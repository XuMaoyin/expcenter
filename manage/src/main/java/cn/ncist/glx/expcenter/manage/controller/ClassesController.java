package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Classes;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import cn.ncist.glx.expcenter.manage.service.ClassesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("class")
public class ClassesController {

    @Resource
    ClassesService classesService ;

    /**
     * 查询所有班级
     * @return
     */
    @RequestMapping("classes")
    public ResultVO getAll(){
        return classesService.getAll();
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody Classes classes){
        return classesService.add(classes);
    }


    @PostMapping("import")
    public ResultVO imp(@RequestBody Classes[] list){
        return classesService.imp(list);
    }


    @GetMapping("class")
    public ResultVO getOne(int id){
        return classesService.getOne(id);
    }

    @GetMapping("del")
    public ResultVO del(int id){
        return classesService.del(id);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody Classes classes){

        return classesService.update(classes);

    }

}
