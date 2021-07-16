<template>
  <div>
    <div class="headButton">
      <el-button @click="addCourse" type="primary" size="small">添加课程</el-button>
    </div>
    <div>
      <el-table :data="showList" style="width: 100%;" height="510px">
        <el-table-column prop="name" label="课程名称"></el-table-column>
        <el-table-column prop="class" label="教学班级">
          <template slot-scope="scope">
            <span v-for="item in scope.row.classesList">
              {{ item.className }}&nbsp;
            </span>
          </template>

        </el-table-column>
        <el-table-column prop="people" label="人数"></el-table-column>
        <el-table-column prop="counts" label="实验次数"></el-table-column>
        <el-table-column label="操作" align="center" width="300px">
          <template slot-scope="scope">
            <el-button size="small" type="warning" @click="update(scope.row.id)">修改</el-button>
            <el-button size="small" type="primary" @click="details(scope.row.id)">实验详情</el-button>

            <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="text-align: center;margin-top: 10px">
      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="currentChange"
          :page-size="page.pageSize"
          :current-page="page.currentPage"
          :total="page.total">
      </el-pagination>
    </div>
    <div class="el-dialog">
      <add-course-dialog ref="addCourseRef"></add-course-dialog>
      <update-course-dialog ref="detailsRef"></update-course-dialog>
    </div>
  </div>

</template>

<script>
import AddCourseDialog from './dialog/AddCourseDialog.vue'
import UpdateCourseDialog from './dialog/UpdateCourseDialog.vue'

export default {
  name: 'courseAdmin',
  components: {
    AddCourseDialog,
    UpdateCourseDialog
  },
  mounted() {
    this.tid = JSON.parse(sessionStorage.getItem("user")).id;

    this.loadList()


  },
  methods: {
    loadList() {
      this.$axios.get("/course/courses/teacher?tid="+this.tid).then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message)
          return
        }
        this.dataList = res.data.data;
        this.page.total = this.dataList.length;
        //console.log(this.page.total)
        this.currentChange(1)
      })
    },
    currentChange(val){
      const end = val * this.page.pageSize;
      const start = (val - 1) * this.page.pageSize;
      this.showList = this.dataList.slice(start,end);
    },
    del(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/course/del?id=" + id).then(res => {
          if (res.data.code == 500) {
            this.$message(res.data.message);
            return
          }
        });
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.loadList()

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    addCourse() {
      this.$refs.addCourseRef.openDialog();

    },
    update(id) {
      this.$refs.detailsRef.courseId = id;
      this.$refs.detailsRef.openDialog();
    },
    details(id) {
      this.$router.push({ //核心语句
        path: '/teacher/experiment', //跳转的路径
        query: { //路由传参时push和query搭配使用 ，作用时传递参数
          courseId: id,
        }
      })
    }
  }

  ,
  data() {
    return {
      showList:[],
      dataList: [{}],
      page:{
        total:0,
        pageSize:8,
        currentPage:1,
      },
      tid : '',
    }
  },

}
</script>

<style>
.headButton {
  margin: 10px auto;
}
</style>
