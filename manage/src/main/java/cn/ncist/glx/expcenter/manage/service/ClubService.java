package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Club;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ClubMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClubService {

    @Resource
    ClubMapper clubMapper;

    public ResultVO add(Club club){
        try {
            clubMapper.insert(club);
            return ResultVO.getSuccess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }
    }

    public ResultVO del(int id){
        try {
            clubMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultVO.getFail("系统错误，删除失败");
        }
    }

    public ResultVO update(Club club){
        try {
            clubMapper.updateByPrimaryKey(club);
            return ResultVO.getSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，修改失败");
        }
    }

    public ResultVO getAll(){
        return ResultVO.getSuccess("查询成功",clubMapper.selectAll());
    }

    public ResultVO getOne(int id){
        return ResultVO.getSuccess("查询成功",clubMapper.selectByPrimaryKey(id));
    }
}
