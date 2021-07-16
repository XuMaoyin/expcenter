package cn.ncist.glx.expcenter.manage.service;

import cn.ncist.glx.expcenter.beans.po.*;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.utils.JSONUtil;
import cn.ncist.glx.expcenter.beans.vo.ResultVO;
import cn.ncist.glx.expcenter.manage.mapper.*;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Resource
    CourseMapper courseMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ClassesMapper classesMapper;

    @Resource
    ExperimentMapper experimentMapper;

    @Resource
    ClassroomMapper classroomMapper ;

    /**
     * 查询所有课程（分页）
     * @param nowPage 当前页
     * @param length 每页数据条数
     * @return  返回查询结果使用ResultVO类包装
     */
    public ResultVO getAllLimit(String nowPage, String length){

        List<Course> list = courseMapper.selectAllLimit(nowPage,length);

        return ResultVO.getSuccess("成功",list);
    }

    /**
     * 查询所有课程
     * @return 返回查询结果使用ResultVO类包装
     */
    public ResultVO getAll(){
        try {
            List<Course> list = courseMapper.selectAll();
            //处理教师和班级信息
            for(Course course:list){
                setCourse(course);
            }
            return ResultVO.getSuccess("success",list) ;
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("系统错误，查询失败");
        }


    }

    /**
     * 处理课程的班级，人数，和教师信息
     * @param course 要处理的课程
     */
    public void setCourse(Course course){
        //处理教师信息
        Teacher teacher = teacherMapper.selectByPrimaryKey(course.getLeaderId());
        course.setTeacher(teacher);
        //处理班级信息
        String classIds = course.getClassIds();
        List<Integer> integers = JSONUtil.jsonToList(classIds);
        List<Classes> classesList = new ArrayList<>();

        int people = 0;
        for(Integer id:integers){
            Classes classes = classesMapper.selectByPrimaryKey(id);

            people += Integer.parseInt( classes.getPeople());
            classesList.add(classes);
        }

        course.setPeople(String.valueOf(people));
        course.setClassesList(classesList);

    }

    /**
     * 添加课程
     * @param course 要添加的课程信息
     * @return 返回添加结果使用ResultVO类包装
     */
    public ResultVO add(Course course){
        try {

            setCourse(course);

            courseMapper.insert(course);
            return ResultVO.getSuccess("success");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("error");
        }
    }

    /**
     * 更新课程信息
     * @param course 更新的课程信息
     * @return 返回查询结果使用ResultVO类包装
     */
    public ResultVO update(Course course){
        try {

            setCourse(course);
            courseMapper.updateByPrimaryKey(course);

            return ResultVO.getSuccess("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return  ResultVO.getFail("系统错误，更新失败");
        }
    }

    /**
     * 根据id删除课程
     * @param id 要删除的课程的id
     * @return 返回查询结果使用ResultVO类包装
     */
    public ResultVO del(Integer id){

        try {
            courseMapper.deleteByPrimaryKey(id);
            return ResultVO.getSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return  ResultVO.getFail("系统错误，删除失败");
        }
    }

    /**
     * 根据教师id查询对应教师的课程
     * @param tid 教师的id
     * @return 返回查询结果使用ResultVO类包装
     */
    public ResultVO getByTeacherId(int tid){

        List< Course> list = courseMapper.selectByTacherId(tid);
        for(Course course:list){
            setCourse(course);
        }
        return ResultVO.getSuccess("success",list);
    }

    /**
     * 根据课程id查询
     * @param id 课程id
     * @return 返回查询结果使用ResultVO类包装
     */
    public ResultVO getOne(int id) {

        Course course = courseMapper.selectByPrimaryKey(id);
        //setCourse(course);
       // System.out.println(course);
        return ResultVO.getSuccess("查询成功", course);
    }

    /**
     * 解析导入课表上传的json数据
     * @param courses
     * @return
     */
    public ResultVO imp(Course[] courses) {
        try {
            //完善课程并导入
            for (Course course : courses) {
                //查询是否已经存在该课程
                Course course1 = courseMapper.selectCourseName(course.getName());
                if (course1 == null) {
                    //设置leaderId
                    String nickname = course.getTeacher().getNickname();
                    Teacher teacher = teacherMapper.selectByNickname(nickname).get(0);
                    course.setLeaderId(teacher.getId());
                    //设置班级
                    String classes = course.getClasses();
                    String[] strings = classes.split(" ");
                    List<Integer> arr = new ArrayList<>();
                    int people = 0;
                    for (String str : strings) {
                        //System.out.println(str);
                        Classes c1 = classesMapper.selectByName(str);
                        people += Integer.parseInt(c1.getPeople());
                        arr.add(c1.getId());
                        //System.out.println(str+"\t"+c1.getId());
                    }
                    String s = JSONUtil.listToJson(arr);
                    course.setClassIds(s);
                    //设置课时
                    course.setCounts(String.valueOf(course.getExperiment().size()));
                    //设置课程人数
                    course.setPeople(people + "");
                    //System.out.println(course.getCounts());

                    //执行导入，
                    courseMapper.insert(course);
                    //course.setId(Integer.parseInt(course.getCounts()));
                } else {
                    //若存在,则设置上课程id
                    course.setId(course1.getId());
                }


                //System.out.println(course.getId());

                //开始实验部分
                for (Experiment experiment : course.getExperiment()) {

                    //System.out.println(experiment);
                    //设置课程id
                    experiment.setCourseId(course.getId());
                    //设置教室id
                    String number = experiment.getClassroom().getNumber();
                    System.out.println(number);
                    Classroom classroom = classroomMapper.selectByNumber(number);
                    experiment.setRoomId(classroom.getId());
                    //设置日期
                    experiment.setDatetime(DateUtil.stringToDate(experiment.getDate()));
                    //设置节次
                    experiment.setNumber(DateUtil.weakToNumber(experiment.getWeak()));
                    System.out.println(experiment);
                    //插入到数据库
                    experimentMapper.insert(experiment);
                }

                return ResultVO.getSuccess("导入成功");

            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVO.getFail("数据格式错误，导入失败");

        }

        return  null;

    }
}
