package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Activity;
import cn.ncist.glx.expcenter.beans.po.Classroom;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ActivityMapper;
import cn.ncist.glx.expcenter.manage.mapper.ClassroomMapper;
import cn.ncist.glx.expcenter.manage.mapper.ExperimentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomService {

    @Resource
    ClassroomMapper roomMapper;

    @Resource
    ActivityMapper activityMapper ;

    @Resource
    ExperimentMapper experimentMapper;

    public ResultVO getAll(){
        List<Classroom> list = roomMapper.selectAll();
        for (Classroom classroom:list){
            setRoom(classroom);
        }
        return ResultVO.getSuccess("查询成功", list);
    }


    public ResultVO add(Classroom classroom){

        try {
            roomMapper.insert(classroom);
            return ResultVO.getSuccess("添加成功");
        }catch ( Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }

    }

    public ResultVO del (int id){
        try {
            roomMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch ( Exception e){
            e.printStackTrace();
            return  ResultVO.getFail("系统错误，删除失败");
        }
    }

    public ResultVO update(Classroom classroom){
        try {
            roomMapper.updateByPrimaryKey(classroom);
            return ResultVO.getSuccess("更新成功");

        }catch (Exception e){
            e.printStackTrace();
            return  ResultVO.getFail("系统错误，更新失败");
        }
    }

    public ResultVO getOne(int id) {

        return ResultVO.getSuccess("查询成功",roomMapper.selectByPrimaryKey(id));
    }

    public void  setRoom(Classroom classroom){
        int a  = activityMapper.getRoomCount(classroom.getId());
        int b  = experimentMapper.getRoomCount(classroom.getId());
        classroom.setCount(a+b);
    }
}
