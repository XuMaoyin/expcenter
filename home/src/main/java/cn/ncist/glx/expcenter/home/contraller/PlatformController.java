package cn.ncist.glx.expcenter.home.contraller;

import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.home.service.PlatformService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("platform")
public class PlatformController {

    @Resource
    PlatformService platformService;

    /**
     * 获取所有在线实验平台
     * @return
     */
    @GetMapping("platforms")
    public ResultVO getAll(){
        return platformService.getAll();
    }

    @GetMapping("getFive")
    public ResultVO getFive(){
        return platformService.getFive();
    }

}
