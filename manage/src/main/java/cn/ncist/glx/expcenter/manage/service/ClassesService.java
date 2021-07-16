package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Classes;
import cn.ncist.glx.expcenter.beans.po.Course;
import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.ClassesMapper;
import cn.ncist.glx.expcenter.manage.mapper.CourseMapper;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesService {

    @Resource
    ClassesMapper classesMapper;

    @Resource
    TeacherMapper teacherMapper ;

    @Resource
    CourseMapper courseMapper ;

    /**
     * 获取所有班级
     * @return
     */
    public ResultVO getAll() {

        try {
            List<Classes> list = classesMapper.selectAll();
            for (Classes classes:list){
                classes.setTeacher( teacherMapper.selectByPrimaryKey(classes.getLeaderId()));
                List<Course> courses =  courseMapper.selectByClassesId(classes.getId());
                for (Course course:courses){
                    course.setTeacher(teacherMapper.selectByPrimaryKey(course.getLeaderId()));
                }
                classes.setCourses(courses);
                classes.setCount(courses.size());
            }
            return ResultVO.getSuccess("查询成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，查询失败");
        }
    }


    /**
     * 添加班级
     * @param classes
     * @return
     */
    public ResultVO add(Classes classes){
        try {
            classesMapper.insert(classes);
            return ResultVO.getSuccess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误,插入失败");
        }

    }

    public ResultVO update(Classes classes) {

        try {
            classesMapper.updateByPrimaryKey(classes);
            return ResultVO.getSuccess("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，更新失败");
        }
    }

    public ResultVO getOne(int id) {

        return ResultVO.getSuccess("查询成功",classesMapper.selectByPrimaryKey(id));
    }

    public ResultVO del(int id) {
        try {
            classesMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    public ResultVO imp(Classes[] list) {
            for (Classes classes:list){
                int id = 1 ;
                try {
                    List<Teacher> teachers = teacherMapper.selectByNickname(classes.getLeader());
                    id = teachers.get(0).getId();
                }catch (Exception e){
                    e.printStackTrace();
                }
                classes.setLeaderId(id);
                //System.out.println(classes);
                this.add(classes);
            }

            return ResultVO.getSuccess("导入成功，请自行检查数据是否一致");

    }
}
