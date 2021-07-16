package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Config;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigService {

    @Resource
    ConfigMapper configMapper;

    /**
     * 获取当前的配置项
     * @return
     */
    public ResultVO getConfigs(){
        List<Config> list = configMapper.selectAll();
        //转化为map再返回
        Map<String,String> map = new HashMap();
        for(Config config:list){
            map.put(config.getKey(),config.getValue());
        }
        return ResultVO.getSuccess("成功",map) ;

    }

    /**
     * 根据key互殴去对应的value
     * @param key
     * @return
     */
    public ResultVO getConfig(String key){
        return ResultVO.getSuccess("成功",configMapper.selectByPrimaryKey(key));
    }

    /**
     * 更新配置
     * @param config
     * @return
     */
    public ResultVO update(Config config){
//        System.out.println(config);
        try {
            configMapper.updateByPrimaryKey(config);
            return ResultVO.getSuccess("成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，更新失败");
        }

    }

    /**
     * 根据日期转周数
     * @param date
     * @return
     */
    public ResultVO getWeak(String date) {
        //获取学期起始时间
        String startsTime = configMapper.selectByPrimaryKey("startsTime").getValue();
        //获取当前周数
        String weak = DateUtil.getWeak(startsTime, date);

        return ResultVO.getSuccess("success",weak);
    }
}
