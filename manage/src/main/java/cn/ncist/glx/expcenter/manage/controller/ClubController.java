package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Club;
import cn.ncist.glx.expcenter.beans.utils.UsernameUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.service.ClubService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("club")
public class ClubController {

    @Resource
    ClubService clubService;

    @PostMapping("add")
    public ResultVO add(@RequestBody Club club){
        club.setUsername(UsernameUtil.createUsername());
        return clubService.add(club);
    }

    @GetMapping("del")
    public ResultVO del(int id){
        return clubService.del(id);
    }
    @PostMapping("update")
    public ResultVO update(@RequestBody Club club){
        return clubService.update(club);
    }

    @GetMapping("clubs")
    public ResultVO getAll(){
        return clubService.getAll();
    }

    @GetMapping("club")
    public ResultVO getOne(int id){
        return clubService.getOne(id);
    }
}
