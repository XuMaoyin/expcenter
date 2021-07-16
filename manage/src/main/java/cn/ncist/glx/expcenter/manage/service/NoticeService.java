package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Notice;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    /**
     * 发布公告
     * @param notice
     * @return
     */
    public ResultVO add(Notice notice){
        try {
            noticeMapper.insert(notice);

            return ResultVO.getSuccess("发布成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，发布失败");
        }
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    public ResultVO del(int id){
        try {
            noticeMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return  ResultVO .getFail("系统错误，删除失败");
        }
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    public ResultVO update(Notice notice){
        try {
            noticeMapper.updateByPrimaryKey(notice);
            return ResultVO.getSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，修改失败");
        }
    }

    /**
     * 置顶公告
     * @param id
     * @return
     */
    public ResultVO top(int id){
        try {
            noticeMapper.doTop(id);
            return ResultVO.getSuccess("置顶成功");
        }catch ( Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，置顶失败");
        }
    }

    /**
     * 取消置顶
     * @param id
     * @return
     */
    public ResultVO unTop(int id) {
        try {
            noticeMapper.unTop(id);
            return ResultVO.getSuccess("取消置顶成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，取消置顶失败");
        }

    }

    /**
     * 查询所有
     * @return
     */
    public ResultVO getAll(){
        List<Notice> list = noticeMapper.selectAll();
        for (Notice notice:list){
            //设置要显示的日期格式
            notice.setDate(DateUtil.DateToString(notice.getDatetime()));

        }
        return ResultVO.getSuccess("查询成功", list);
    }

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    public ResultVO getOne(int id){
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        notice.setDate(DateUtil.DateToString(notice.getDatetime()));
        return ResultVO.getSuccess("查询成功", notice);
    }

    public ResultVO getByTitle(String title){
        return ResultVO.getSuccess("查询成功",noticeMapper.selectByTitle(title));
    }


}
