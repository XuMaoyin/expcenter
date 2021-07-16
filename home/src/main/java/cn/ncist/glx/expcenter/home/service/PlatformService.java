package cn.ncist.glx.expcenter.home.service;

import cn.ncist.glx.expcenter.beans.po.Platform;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.home.mapper.PlatformMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlatformService {

    @Resource
    PlatformMapper platformMapper;

    public ResultVO getAll(){
        return ResultVO.getSuccess("查询成功",platformMapper.selectAll());
    }

    public ResultVO getFive() {

        List<Platform> platforms = platformMapper.selectAll();

        if(platforms.size()>5){

            List<Platform> list = new ArrayList();
            list.add(platforms.get(0));
            list.add(platforms.get(1));
            list.add(platforms.get(2));
            list.add(platforms.get(3));
            list.add(platforms.get(4));

            return ResultVO.getSuccess("查询成功",list);

        }
        return ResultVO.getSuccess("查询成功",platforms);
    }
}

