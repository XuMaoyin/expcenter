package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Config;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("config")
public class ConfigController {

    @Resource
    ConfigService configService;

    /**
     * 获取当前所有配置
     * @return
     */
    @GetMapping("configs")
    public ResultVO getConfigs(){
        return configService.getConfigs();
    }

    /**
     * 查询一个配置
     * @param key
     * @return
     */
    @GetMapping("config")
    public ResultVO getConfig(String key){
        return configService.getConfig(key);
    }

    /**
     * 更新一条配置
     * @param config
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody Config config){
        return configService.update(config);
    }


    @GetMapping("getWeak")
    public ResultVO getWeak(String date){
        return configService.getWeak(date);
    }
}
