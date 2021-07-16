import Vue from 'vue'
import Router from 'vue-router'

//公共
import ExperimentTable from './views/components/ExperimentTable.vue'
import home from './views/home/Home'

//管理员
import CourseTable from './views/admin/course/CourseTable.vue'
import ApplyCourseTable from './views/admin/apply/ApplyCourseTable.vue'
import ApplyExperimentTable from './views/admin/apply/ApplyExperimentTable.vue'
import ApplyActivityTable from './views/admin/apply/ApplyActivityTable.vue'
import ActivityTeacherTable from './views/admin/activity/ActivityTeacherTable.vue'
import ActivityStudentTable from './views/admin/activity/ActivityStudentTable.vue'
import TeacherTable from './views/admin/teacher/TeacherTable.vue'
import ClassesTable from './views/admin/classes/ClassesTable.vue'
import RoomTable from './views/admin/room/RoomTable.vue'
import NoticeTable from './views/admin/notice/NoticeTable.vue'
import NoticePublish from './views/admin/notice/NoticePublish.vue'
import ClubTable from './views/admin/club/ClubTable.vue'
import PlatformTable from './views/admin/platform/PlatformTable.vue'
import Information from './views/admin/info/Information.vue'

//教师
import TCourseTable from './views/teacher/course/CourseTable.vue'
import TActivityTeacherTable from './views/teacher/activity/ActivityTeacherTable.vue'
import TApplyActivityTable from './views/teacher/apply/ApplyActivityTable.vue'
import TApplyCourseTable from './views/teacher/apply/ApplyCourseTable.vue'
import TApplyExperimentTable from './views/teacher/apply/ApplyExperimentTable.vue'
import TReviewActivityTable from './views/teacher/review/ReviewActivityTable.vue'
import TInformation from './views/teacher/info/Information.vue'


//学生
import SApply from './views/student/apply/ApplyActivityTable.vue'
import SActivity from './views/student/activity/ActivityStudentTable.vue'
import SInformation from './views/student/info/Information.vue'


Vue.use(Router)
//创建路由
const router =new Router({
    //mode: 'history', //去掉路径后的'#'号，但是会导致数据服务器的冲突
    routes: [

        //公共
        //登录页
        {path: '/login', component:()=>import('./views/login/Login.vue')},
        {path: '/404', component:()=>import('./views/404/404.vue')},
        {path: '/400', component:()=>import('./views/404/400.vue')},
        {path:'*',redirect:'/404',hidden:true},
        {path:'/',redirect:'/login'},

        //管理员登录
        {
            path:'/admin/',
            component: home,
            children:[
                //管理员信息页
                {
                    path: 'info',
                    component: Information
                },
                //社团管理
                {
                    path:'basis/club',
                    component: ClubTable,
                },
                //实验平台管理
                {
                    path:'basis/platform',
                    component: PlatformTable,
                },
                //教师管理
                {
                    path: 'basis/teachers',
                    name: 'TeacherTable',
                    component: TeacherTable

                },
                //班级管理
                {
                    path: 'basis/classes',
                    name: 'ClassesTable',
                    component: ClassesTable

                },
                //实验室管理
                {
                    path: 'basis/rooms',
                    name: 'RoomTable',
                    component: RoomTable

                },
                //课程管理
                {
                    path: 'course',
                    name: 'courseTable',
                    component: CourseTable,
                },
                //实验管理
                {
                    path: 'experiment',
                    name: 'ExperimentTable',
                    component: ExperimentTable
                },
                //申请管理
                {
                    path: 'apply/course',
                    name: 'ApplyCourseTable',
                    component: ApplyCourseTable,
                },
                {
                    path: 'apply/experiment',
                    name: 'ApplyExperimentTable',
                    component: ApplyExperimentTable

                }, {
                    path: 'apply/activity',
                    name: 'ApplyActivityTable',
                    component: ApplyActivityTable
                },
                //活动管理
                {
                    path: 'activity/teacher',
                    name: 'ActivityTeacherTable',
                    component: ActivityTeacherTable

                }, {
                    path: 'activity/student',
                    name: 'Activity',
                    component: ActivityStudentTable

                },

                //公告管理
                {
                    path: 'notice/table',
                    name: 'NoticeTable',
                    component: NoticeTable

                }, {
                    path: 'notice/publish',
                    name: 'NoticePublish',
                    component: NoticePublish
                },
            ]
        },
        //教师
        {
           path:'/teacher/',
           component: home,
            children: [
                {
                  path: 'info',
                    component: TInformation
                },
                //我的课程
                {
                    path: 'course',
                    name: 'T-course',
                    component: TCourseTable
                },
                //实验管理
                {
                    path: 'experiment',
                    name: 'ExperimentTable',
                    component: ExperimentTable
                },
                //我的活动
                {
                    path: 'activity',
                    name: 'TActivityTeacherTable',
                    component: TActivityTeacherTable
                },
                //我的申请
                {
                    path: 'apply/course',
                    name: 'TApplyCourseTable',
                    component: TApplyCourseTable
                }, {
                    path: 'apply/experiment',
                    name: 'TApplyExperimentTable',
                    component: TApplyExperimentTable
                }, {
                    path: 'apply/activity',
                    name: 'TApplyActivityTable',
                    component: TApplyActivityTable
                },
                //我的学生申请
                {
                    path: 'review',
                    name: 'TReviewActivityTable',
                    component: TReviewActivityTable
                },
            ]
        },
        //学生
        {
            path:'/student/',
            component: home,
            children: [
                {
                    path: 'info',
                    component: SInformation
                },
                {
                    path: 'apply',
                    name: 'SApply',
                    component: SApply
                }, {
                    path: 'activity',
                    name: 'SActivity',
                    component: SActivity
                }
            ]
        }
    ],
})

//设置路由前置守卫
router.beforeEach((to, from, next) => {
    //console.log(to);
    const path = to.path ;
    //设置登录页、400页和404页默认放行
    if(path === '/login' || path === '/404' || path === '/400') return next();
    //获取当前登录的用户身份
    const identity = sessionStorage.getItem("identity")
    //根据用户身份判断是否有权限访问该路由
    switch (identity){
        //为空时访问登录页
        case null:
            router.push({path: '/login'})
            break;
        case 'null':
            router.push({path:'/login'})
            break;
        case 'admin':
            if(path.startsWith('/admin'))
                return next();
            else
                router.push({path:'/400'})
            break;
        case 'teacher':
            if(path.startsWith('/teacher'))
                return next();
            else
                router.push({path:'/400'})
            break;
        case 'student':
            if(path.startsWith('/student'))
                return next();
            else
                router.push({path:'/400'})
            break;
    }
})

//启用路由
export default router;



