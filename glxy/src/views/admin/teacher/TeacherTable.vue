<template>
  <div>
    <div class="headButton">
      <el-button @click="addTeacher" type="primary" size="small">添加教师</el-button>
      <el-button @click="importTeacher" type="primary" size="small">批量导入</el-button>
    </div>
    <div>
      <el-table :data="showList" style="width: 100%;" :border="false" height="510px">
        <el-table-column prop="nickname" label="姓名"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="department" label="部门"></el-table-column>
        <el-table-column prop="tel" label="电话"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column label="本期课程">
          <template slot-scope="scope">
            <el-popover placement="bottom" trigger="click">
              <div slot="reference">
                <el-tag v-text="scope.row.count">门课</el-tag>
              </div>
              <el-table :data="scope.row.course">
                <el-table-column prop="name" label="课程名"></el-table-column>
                <el-table-column prop="classIds" label="班级"></el-table-column>
                <el-table-column prop="people" label="人数"></el-table-column>
                <el-table-column prop="counts" label="课时"></el-table-column>
              </el-table>

            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="250">
          <template slot-scope="scope">
            <el-button size="small" @click="update(scope.row.id)">修改</el-button>
            <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
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
    </div>

    <div class="el-dialog">
      <add-teacher-dialog ref="addTeacherRef"></add-teacher-dialog>
      <updata-teacher-dialog ref="updateTeacherRef" :teacher="teacher"></updata-teacher-dialog>
      <import-teacher-dialog ref="importTeacherRef"></import-teacher-dialog>
    </div>
  </div>

</template>

<script>
import AddTeacherDialog from './dialog/AddTeacherDialog.vue'
import UpdataTeacherDialog from './dialog/UpdataTeacherDialog.vue'
import ImportTeacherDialog from './dialog/ImportTeacherDialog.vue'

export default {
  name: 'courseAdmin',
  components: {
    AddTeacherDialog,
    UpdataTeacherDialog,
    ImportTeacherDialog
  },
  methods: {
    addTeacher() {
      this.$refs.addTeacherRef.openDialog();

    },
    importTeacher() {
      this.$refs.importTeacherRef.openDialog();
    },
    update(id) {
      this.$refs.updateTeacherRef.teacherId = id;
      this.$refs.updateTeacherRef.openDialog();
    },
    del(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/teacher/del?id=" + id).then(res => {
          if (res.data.code == 500) {
            this.$message(res.data.message);
            return
          }
        });
        this.loadList();
        this.$message({
          type: 'success',
          message: '删除成功!'
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    loadList() {
      this.$axios.get("/teacher/teachers").then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message);
          return
        }

        this.dataList = res.data.data;
        this.page.total = this.dataList.length;
        this.currentChange(1)
      })
    },
    currentChange(val){
      const end = val * this.page.pageSize;
      const start = (val - 1) * this.page.pageSize;
      this.showList = this.dataList.slice(start,end);
    }
  },
  mounted() {
    this.loadList();
  }

  ,
  data() {
    return {
      showList:[{}],
      dataList: [{
        nickname: "小王",
        sex: '男',
        department: '电商',
        tel: '13511111111',
        email: '998866@163.com',
        count: 0,
        course: [
          {courseName: 'xxxx', courseClass: '电商171', coursePeoples: 65, courseHours: 16}
        ]
      }
      ],
      teacher: {},
      page:{
        total:0,
        pageSize:8,
        currentPage:1,
      }
    }
  }
}
</script>

<style>

</style>
