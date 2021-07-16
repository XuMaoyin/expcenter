package cn.ncist.glx.expcenter.home.service;

import cn.ncist.glx.expcenter.beans.po.*;
import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import cn.ncist.glx.expcenter.beans.vo.*;
import cn.ncist.glx.expcenter.home.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    CourseMapper courseMapper;

    @Resource
    ExperimentMapper experimentMapper;

    @Resource
    ClassroomMapper classroomMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ClubMapper clubMapper;

    @Resource
    ActivityMapper activityMapper;


    public ResultVO today() {

        List<Experiment> experiments = experimentMapper.selectToday();

        List<Activity> activities = activityMapper.selectToday();

        DayCourse dayCourse = new DayCourse();

        //处理搜索到的当天课程并赋值到上面的数组中
        for (Experiment experiment : experiments) {
            //获取课程信息，教师信息，教室信息
            Classroom classroom = classroomMapper.selectByPrimaryKey(experiment.getRoomId());
            Course course = courseMapper.selectByPrimaryKey(experiment.getCourseId());
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getLeaderId());
            //拼接成课程-教师的字符串
            String str = course.getName() + "-" + teacher.getNickname();
            //赋值到对应节次的对应教室里
            setNumber(dayCourse, classroom, str, experiment.getNumber());
        }
        //活动安排亦是同理
        for (Activity activity : activities) {
            Classroom classroom = classroomMapper.selectByPrimaryKey(activity.getRoomId());
            //System.out.println(activity);
            String str = activity.getActivityName() + "-";

            if (activity.getType().equals("teacher")) {
                str += teacherMapper.selectByPrimaryKey(activity.getLeaderId()).getNickname();
            } else {
                //System.out.println(activity);
                Integer clubId = activity.getClubId();
                Club club = clubMapper.selectByPrimaryKey(clubId);
                //System.out.println(clubId);
                String nickname = club.getNickname();
                str += nickname;
            }
            setNumber(dayCourse, classroom, str, activity.getNumber());

        }

        dayCourse.setList();

        //System.out.println(dayCourse.getList().toArray());


        ResultVO resultVO = ResultVO.getSuccess("查询成功", dayCourse.getList().toArray());
        resultVO.setCode(0);
        return resultVO;
    }

    //选择节次
    public void setNumber(DayCourse dayCourse, Classroom classroom, String str, Integer number) {
        //System.out.println(number);

        switch (number) {

            case 1:
                if(null == dayCourse.getS1())
                dayCourse.setS1(new DayNumberCourse("1-2节"));
                setRoom(dayCourse.getS1(), classroom, str);
                break;
            case 2:
                if(null == dayCourse.getS2())
                dayCourse.setS2(new DayNumberCourse("3-4节"));
                setRoom(dayCourse.getS2(), classroom, str);
                break;
            case 3:
                if(null == dayCourse.getS3())
                dayCourse.setS3(new DayNumberCourse("5-6节"));
                setRoom(dayCourse.getS3(), classroom, str);
                break;
            case 4:
                if(null == dayCourse.getS4())
                dayCourse.setS4(new DayNumberCourse("7-8节"));
                setRoom(dayCourse.getS4(), classroom, str);
                //System.out.println(todays[3]);
                break;
            case 5:
                if(null == dayCourse.getS5())
                dayCourse.setS5(new DayNumberCourse("9-10节"));
                setRoom(dayCourse.getS5(), classroom, str);
                break;
        }
    }

    //选择教室并赋值上课程信息
    public void setRoom(DayNumberCourse today, Classroom classroom, String str) {

        switch (classroom.getNumber()) {
            case "30901":
                today.setS901(str);
                break;
            case "30902":
                today.setS902(str);
                break;
            case "30903":
                today.setS903(str);
                break;
            case "30904":
                today.setS904(str);
                break;
            case "30905":
                today.setS905(str);
                break;
            case "30906":
                today.setS906(str);
                break;
        }
        //System.out.println(str);
        //System.out.println(classroom.getNumber());
        //System.out.println(today);

    }


    public ResultVO weak() {

        List<Experiment> experiments = experimentMapper.selectWeak();

        List<Activity> activities = activityMapper.selectWeak();

        //System.out.println(experiments);
        //System.out.println(activities);

        WeakCourse weakCourse = new WeakCourse();

        for (Experiment experiment : experiments) {

            Classroom classroom = classroomMapper.selectByPrimaryKey(experiment.getRoomId());
            Course course = courseMapper.selectByPrimaryKey(experiment.getCourseId());
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getLeaderId());
            //拼接成课程-教师的字符串
            String str = course.getName() + "-" + teacher.getNickname() + "@" + classroom.getNumber();
            int weak = DateUtil.dateToWeak(experiment.getDatetime());
            Integer number = experiment.getNumber();

            setNumber2(number,weakCourse,weak,str);
        }
        for(Activity activity:activities){
            Classroom classroom = classroomMapper.selectByPrimaryKey(activity.getRoomId());
            //System.out.println(activity);
            String str = activity.getActivityName() + "-";

            if (activity.getType().equals("teacher")) {
                str += teacherMapper.selectByPrimaryKey(activity.getLeaderId()).getNickname();
            } else {
                //System.out.println(activity);
                Integer clubId = activity.getClubId();
                Club club = clubMapper.selectByPrimaryKey(clubId);
                //System.out.println(clubId);
                String nickname = club.getNickname();
                str += nickname;
            }
            str += "@" + classroom.getNumber();
            int weak = DateUtil.dateToWeak(activity.getDatetime());
            Integer number = activity.getNumber();

            setNumber2(number,weakCourse,weak,str);


        }
        weakCourse.setList();

        Object[] array = weakCourse.getList().toArray();
        //System.out.println(array);

        ResultVO resultVO = ResultVO.getSuccess("查询成功", array);
        resultVO.setCode(0);
        return resultVO;
    }

    public void setRoom2(int weak, WeakNumberCourse weakNumberCourse, String str) {
        switch (weak) {
            case 1:
                weakNumberCourse.setMonday(str);
                break;
            case 2:
                weakNumberCourse.setTuesday(str);
                break;
            case 3:
                weakNumberCourse.setWednesday(str);
                break;
            case 4:
                weakNumberCourse.setThursday(str);
                break;
            case 5:
                weakNumberCourse.setFriday(str);
                break;
            case 6:
                weakNumberCourse.setSaturday(str);
                break;
            case 7:
                weakNumberCourse.setSunday(str);
                break;

        }
    }

    public void setNumber2(Integer number,WeakCourse weakCourse,int weak ,String str){
        switch (number) {
            case 1:
                setRoom2(weak, weakCourse.getS1(), str);
                break;
            case 2:
                setRoom2(weak, weakCourse.getS2(), str);
                break;
            case 3:
                setRoom2(weak, weakCourse.getS3(), str);
                break;
            case 4:
                setRoom2(weak, weakCourse.getS4(), str);
                break;
            case 5:
                setRoom2(weak, weakCourse.getS5(), str);
                break;

        }
    }
}
