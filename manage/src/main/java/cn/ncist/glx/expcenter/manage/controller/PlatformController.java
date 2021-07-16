package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Platform;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.PlatformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("platform")
public class PlatformController {

    @Resource
    PlatformService platformService;

    @PostMapping("add")
    public ResultVO add(@RequestBody Platform platform) {
        return platformService.add(platform);
    }

    @GetMapping("del")
    public ResultVO del(int id){
        return  platformService.del(id);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody Platform platform){
        return platformService.update(platform);
    }

    @GetMapping("platforms")
    public ResultVO getAll(){
        return platformService.getAll();
    }

    @GetMapping("platform")
    public ResultVO getOne(int id){
        return platformService.getOne(id);
    }
}
