package cn.ncist.glx.expcenter.home.mapper;

import cn.ncist.glx.expcenter.beans.po.Experiment;
import java.util.List;

public interface ExperimentMapper {


    List<Experiment> selectToday();

    List<Experiment> selectWeak();
}