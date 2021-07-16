package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Experiment;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.ExperimentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("experiment")
public class ExpController {

    @Resource
    ExperimentService experimentService;

    @GetMapping("experiments")
    public ResultVO getByCourseId(int courseId){
        return experimentService.getByCourseId(courseId);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody Experiment experiment){
        return experimentService.update(experiment);
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody Experiment experiment){
        //System.out.println(experiment);
        return experimentService.add(experiment);
    }

    @PostMapping("change")
    public ResultVO change(@RequestBody List<Experiment> list){
        //System.out.println(list);
        return experimentService.change(list);
    }

    @GetMapping("del")
    public ResultVO del(int id){
        return experimentService.del(id);
    }


}
