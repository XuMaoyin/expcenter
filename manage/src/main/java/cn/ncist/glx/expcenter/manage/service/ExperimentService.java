package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Experiment;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ClassroomMapper;
import cn.ncist.glx.expcenter.manage.mapper.ConfigMapper;
import cn.ncist.glx.expcenter.manage.mapper.CourseMapper;
import cn.ncist.glx.expcenter.manage.mapper.ExperimentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ExperimentService {

    @Resource
    ExperimentMapper experimentMapper ;

    @Resource
    CourseMapper courseMapper ;

    @Resource
    ClassroomMapper classroomMapper ;

    @Resource
    ConfigMapper configMapper;

    /**
     * 查询所有
     * @return
     */
    public ResultVO getAll(){
        List<Experiment> list = experimentMapper.selectAll();
        for (Experiment experiment:list){
            setExperiment(experiment);
        }
        return ResultVO.getSuccess("查询成功",list);
    }

    /**
     * 查询某个课程的实验
     * @param courseId
     * @return
     */
    public ResultVO getByCourseId(int courseId){
        try {
            List<Experiment> list = experimentMapper.selectByCourseId(courseId);
            for (Experiment experiment:list){
                if(experiment.getDatetime()!=null)
                setExperiment(experiment);
            }
            return ResultVO.getSuccess("查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("查询失败");
        }
    }


    /**
     * 设置课程和实验室室信息
     * @param experiment
     */
    public void setExperiment(Experiment experiment){

        String date = DateUtil.DateToString(experiment.getDatetime());
        experiment.setDate(date);
        String weak = DateUtil.getWeak(configMapper.selectByPrimaryKey("startsTime").getValue(), date);
        experiment.setWeak(weak);
        experiment.setCourse(courseMapper.selectByPrimaryKey(experiment.getCourseId()));
        experiment.setClassroom(classroomMapper.selectByPrimaryKey(experiment.getRoomId()));

    }

    /**
     * 修改单个实验信息
     * @param experiment
     * @return
     */
    public ResultVO update(Experiment experiment){
        try {
            Date date = DateUtil.stringToDate(experiment.getDate());
            if(date == null){
                ResultVO.getFail("日期格式错误");
            }
            experiment.setDatetime(date);
            experimentMapper.updateByPrimaryKey(experiment);
            return ResultVO.getSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，修改失败");
        }
    }

    /**
     * 批量修改
     * @param list
     * @return
     */
    public ResultVO change(List<Experiment> list){
        for (Experiment experiment:list){
            ResultVO resultVO = update(experiment);
            if(resultVO.getCode() == 500){
                return resultVO;
            }
        }

        return ResultVO.getSuccess("批量修改成功");


    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    public ResultVO del(int id){
        try {
            experimentMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    /**
     * 添加实验
     * @param experiment
     * @return
     */
    public ResultVO add(Experiment experiment) {
        try {

            experiment.setDatetime(DateUtil.stringToDate(experiment.getDate()));
            experimentMapper.insert(experiment);
            return ResultVO.getSuccess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }
    }
}
