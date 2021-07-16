package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Classroom;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("room")
public class RoomController {

    @Resource
    RoomService roomService;

    @GetMapping("rooms")
    public ResultVO getAll(){
        return roomService.getAll();
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody Classroom classroom){
        return roomService.update(classroom);
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody Classroom classroom){
        return roomService.add(classroom);
    }

    @GetMapping("room")
    public ResultVO getOne(int id){
        return roomService.getOne(id);
    }


    @GetMapping("del")
    public ResultVO del(int id){
        return roomService.del(id);
    }

}
