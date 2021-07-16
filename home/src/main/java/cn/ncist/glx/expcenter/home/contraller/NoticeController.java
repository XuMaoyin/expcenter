package cn.ncist.glx.expcenter.home.contraller;

import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.home.service.NoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("notices")
    public ResultVO getAll(){
        return noticeService.getAll();
    }

    /**
     * 根据id查询一条数据
     * @param id
     * @return
     */
    @GetMapping("notice")
    public ResultVO getOne(int id){
        return noticeService.getOne(id);
    }

    @GetMapping("getFive")
    public ResultVO getFive(){
        return noticeService.getFive();
    }
}
