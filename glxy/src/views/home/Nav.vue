<template>
  <el-menu :default-active="getRoute()" :unique-opened="true" class="el-menu-vertical-demo" @open="handleOpen"
           @close="handleClose" :router='true' :collapse="isCollapse">
    <template v-for="item in navItems">
      <el-menu-item :index="item.index" v-if="item.list == null">
        <i :class="item.icon"></i>
        <span slot="title">{{ item.title }}</span>
      </el-menu-item>
      <el-submenu :index="item.index" v-if="item.list != null">
        <template>
          <span slot="title"><i :class="item.icon"></i>{{ item.title }}</span>
        </template>
        <el-menu-item-group v-for="second in item.list" :key="second.index">
          <el-menu-item :index="second.index">{{ second.title }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </template>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: false,
      navItems: [],
      adminItems: [
        {
          index: '/admin/info',
          icon: 'el-icon-tickets',
          title: '基础设置',
          list: null
        },{
          index: '/admin/basis',
          icon: 'el-icon-tickets',
          title: '基础信息管理',
          list: [
            {index:'/admin/basis/teachers',title: '教师管理'},
            {index:'/admin/basis/club',title: '社团管理'},
            {index:'/admin/basis/classes',title: '班级管理'},
            {index:'/admin/basis/rooms',title: '实验室管理'},
            {index:'/admin/basis/platform',title: '实验平台管理'},
          ]
        }, {
          index: '/admin/course',
          icon: 'el-icon-tickets',
          title: '课程管理',
          list: null
        },
        {
          index: '/admin/apply',
          icon: 'el-icon-s-comment',
          title: '审核管理',
          list: [
          /*    {
            index: '/admin/apply/course',
            title: '课程调整审核'
          }, {
            index: '/admin/apply/experiment',
            title: '实验调整审核'
          }, */
            {
            index: '/admin/apply/activity',
            title: '活动申请审核'
          },]
        }, {
          index: '/admin/activity',
          icon: 'el-icon-message',
          title: '活动管理',
          list: [{
            index: '/admin/activity/teacher',
            title: '教师申请'
          }, {
            index: '/admin/activity/student',
            title: '学生申请'
          },]
        },
        {
          index: '/admin/notice',
          icon: 'el-icon-news',
          title: '公告管理',
          list: [{
            index: '/admin/notice/table',
            title: '公告管理'
          }, {
            index: '/admin/notice/publish',
            title: '发布公告'
          },]
        },
      ],
      teacherItems: [
        {
          index: '/teacher/info',
          icon: 'el-icon-info',
          title: '我的信息',
          list: null
        }, {
          index: '/teacher/course',
          icon: 'el-icon-tickets',
          title: '我的课程',
          list: null
        },
        /*{
          index: '/teacher/apply',
          icon: 'el-icon-s-comment',
          title: '我的申请',
          list: [{
            index: '/teacher/apply/course',
            title: '课程申请'
          }, {
            index: '/teacher/apply/experiment',
            title: '实验申请'
          }, {
            index: '/teacher/apply/activity',
            title: '活动申请'
          }]
        }, */
        {
          index: '/teacher/activity',
          icon: 'el-icon-message',
          title: '我的活动',
          list: null
        }, {
          index: '/teacher/review',
          icon: 'el-icon-receiving',
          title: '我的学生申请',
          list: null
        }
      ],
      studentItems: [
        {
          index: '/student/info',
          icon: 'el-icon-info',
          title: '我的信息',
          list: null
        },
        /*{
          index: '/student/apply',
          icon: 'el-icon-receiving',
          title: '我的申请',
          list: null
        }, */
        {
          index: '/student/activity',
          icon: 'el-icon-message',
          title: '我的活动',
          list: null
        }
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    //获取当前路由，并返回给导航菜单默认选项
    getRoute() {
      var str = this.$route.path
      return (str.substr(1))
    }
  },
  mounted() {
    const identity = sessionStorage.getItem('identity');
    // console.log('identity = '+identity);
    switch (identity) {
      case 'admin':
        this.navItems = this.adminItems;
        break;
      case 'teacher':
        this.navItems = this.teacherItems;
        break;
      case 'student':
        this.navItems = this.studentItems;
        break;

    }

  }
}
</script>

<style>
</style>
