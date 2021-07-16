package cn.ncist.glx.expcenter.home.service;

import cn.ncist.glx.expcenter.beans.po.Notice;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.home.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {
    @Resource
    NoticeMapper noticeMapper;

    public ResultVO getAll(){
        List<Notice> list = noticeMapper.selectAll();
        for (Notice notice:list){
            setNotice(notice);
        }
        return ResultVO.getSuccess("查询成功", list);
    }

    public ResultVO getOne(int id){
        return  ResultVO.getSuccess("查询成功",noticeMapper.selectByPrimaryKey(id));
    }

    public void setNotice(Notice notice){
        String s = DateUtil.DateToString(notice.getDatetime());
        notice.setContent("");
        notice.setDate(s);
    }

    public ResultVO getFive() {
        List<Notice> list = noticeMapper.selectAll();
        if(list.size()>5){
            List<Notice> list1 = new ArrayList<>();
            list1.add(list.get(0));
            list1.add(list.get(2));
            list1.add(list.get(3));
            list1.add(list.get(4));
            list1.add(list.get(5));

            for (Notice notice:list1){
                setNotice(notice);
            }

            return ResultVO.getSuccess("查询成功", list1);
        }

        for (Notice notice:list){
            setNotice(notice);
        }

        return ResultVO.getSuccess("查询成功", list);
    }
}
