package cn.ncist.glx.expcenter.manage.controller;

import cn.ncist.glx.expcenter.beans.po.Notice;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.beans.vo.User;
import cn.ncist.glx.expcenter.manage.service.NoticeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Resource
    NoticeService noticeService ;

    /**
     * 发布公告
     * @param notice
     * @return
     */
    @PostMapping("add")
    public ResultVO add(@RequestBody Notice notice){

        //设置当前时间为发布时间
        Date datetime = new Date();
        notice.setDatetime(datetime);
        //设置置顶时间为发布时间
        notice.setTopTime(datetime);
        //设置点击量为0
        notice.setHits(0);
        return noticeService.add(notice);
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @GetMapping("del")
    public ResultVO del(int id){
        return noticeService.del(id);
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody Notice notice){
        return noticeService.update(notice);
    }

    /**
     * 置顶公告
     * @param id
     * @return
     */
    @GetMapping("top")
    public ResultVO top(int id){
        return noticeService.top(id);
    }

    /**
     * 取消置顶
     * @param id
     * @return
     */
    @GetMapping("unTop")
    public ResultVO unTop(int id){
        return noticeService.unTop(id);
    }

    /**
     * 查询所有公告
     * @return
     */
    @GetMapping("notices")
    public ResultVO getAll(){
        return noticeService.getAll();
    }

    /**
     * 根据id查询一条公告
     * @param id
     * @return
     */
    @GetMapping("notice")
    public ResultVO getOne(int id){
        return noticeService.getOne(id);
    }

    @PostMapping("upload")
    public ResultVO uploadImg( @RequestParam("img") MultipartFile file){
        String path = this.getClass().getResource("/").getPath();
        //System.out.println(path);
        String uuid = UUID.randomUUID().toString();
        String newfilepath = path+ "/static/upload/"+uuid +".jpg";
        try {
            //将文件放到服务器器中的某一个地方
            //转存
            file.transferTo(new File(newfilepath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(name);
        return ResultVO.getSuccess("success","/api/upload/"+uuid+".jpg");
    }
}
