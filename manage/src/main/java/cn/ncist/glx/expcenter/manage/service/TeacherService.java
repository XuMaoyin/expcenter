package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.Course;
import cn.ncist.glx.expcenter.beans.po.Teacher;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.CourseMapper;
import cn.ncist.glx.expcenter.manage.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    TeacherMapper teacherMapper ;

    @Resource
    CourseMapper courseMapper ;

    /**
     * 调用 mapper更新当前用户的信息，更新成功无返回信息，更新失败返回原理来的信息
     * @param teacher
     * @return
     */
    public ResultVO updateInfo(Teacher teacher) {
        Teacher teacher1 =null;


        try {

            //获取更新前的数据
            teacher1 = teacherMapper.selectByPrimaryKey(teacher.getId());

            //判断用户名是否存在
            if(isExist(teacher.getUsername(),teacher.getId())){
                return  ResultVO.getFail("当前用户名以存在",teacher1);
            }

            //更新信息
            teacherMapper.updateByPrimaryKey(teacher);
            //返回更新提示
            return ResultVO.getSuccess("更新信息成功");
        }catch (Exception e){
            e.printStackTrace();
            //执行系统写入失败时的提示
            teacher1 = teacherMapper.selectByPrimaryKey(teacher.getId());
            return ResultVO.getFail("系统错误，更新信息失败",teacher1);
        }

    }

    /**
     * 判断用户名是已存在
     * @param username
     * @return
     */
    public boolean isExist(String username,int id){
            Teacher teacher = teacherMapper.selectByUsername(username);
            if(teacher == null){
                return false;
            }
            if(teacher.getId() == id){
                return false;
            }
            return false;

    }

    public ResultVO getAll() {
        try {
            List<Teacher> list = teacherMapper.selectAll();
            list = getCourse(list);
            return ResultVO.getSuccess("查询成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，查询失败");
        }
    }

    /**
     * 查询教师对应教授的课程
     * @param list
     * @return
     */
    public List<Teacher> getCourse(List<Teacher> list){
        for (Teacher teacher:list){
            List<Course> course = courseMapper.selectByTacherId(teacher.getId());
            teacher.setCourse(course);
            teacher.setCount(course.size());
        }

        return  list;
    }

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    public ResultVO add(Teacher teacher) {
        try {
            teacherMapper.insert(teacher);
            return ResultVO.getSuccess("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，添加失败");
        }

    }

    /**
     * 通过教师id查询
     * @param id
     * @return
     */
    public ResultVO getById(int id) {
        return ResultVO.getSuccess("查询成功",teacherMapper.selectByPrimaryKey(id));
    }

    public ResultVO del(int id) {
        try {
            teacherMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，删除失败");
        }
    }

    public int getIdByName(String name) {
        System.out.println(name);
        List<Teacher> list = teacherMapper.selectByNickname(name);
        try {
            return list.get(0).getId();
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

    }
}
