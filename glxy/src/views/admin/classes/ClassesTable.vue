<template>
  <div class="el-main">
    <div class="headButton">
      <el-button @click="addClasses" type="primary" size="small">添加班级</el-button>
      <el-button @click="importClasses" type="primary" size="small">批量导入</el-button>
    </div>
    <div>
      <el-table :data="showList" style="width: 100%;" :border="false" height="510px">
        <el-table-column prop="className" label="班级名称"></el-table-column>
        <el-table-column prop="desc" label="描述"></el-table-column>
        <el-table-column prop="people" label="人数"></el-table-column>
        <el-table-column prop="teacher.nickname" label="班主任"></el-table-column>
        <el-table-column prop="teacher.tel" label="联系方式"></el-table-column>
        <el-table-column label="本期课程">
          <template slot-scope="scope">
            <el-popover placement="bottom" trigger="click">
              <div slot="reference">
                <el-tag>{{ scope.row.count }}门课</el-tag>
              </div>
              <el-table :data="scope.row.courses">
                <el-table-column prop="name" label="课程名"></el-table-column>
                <el-table-column prop="teacher.nickname" label="任课教师"></el-table-column>
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
      <add-classes-dialog ref="addClassesRef"></add-classes-dialog>
      <updata-Classes-dialog ref="updateClassesRef"></updata-Classes-dialog>
      <import-Classes-dialog ref="importClassesRef"></import-Classes-dialog>
    </div>
  </div>

</template>

<script>
import AddClassesDialog from './dialog/AddClassesDialog.vue'
import UpdataClassesDialog from './dialog/UpdataClassesDialog.vue'
import ImportClassesDialog from './dialog/ImportClassesDialog.vue'

export default {
  name: 'courseAdmin',
  components: {
    AddClassesDialog,
    UpdataClassesDialog,
    ImportClassesDialog
  },
  mounted() {
    this.loadList();
  },
  methods: {
    //获取课程列表
    loadList() {
      this.$axios.get("/class/classes").then(res => {
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

    addClasses() {
      this.$refs.addClassesRef.openDialog();

    },
    importClasses() {
      this.$refs.importClassesRef.openDialog();
    },
    update(id) {
      this.$refs.updateClassesRef.classId = id;
      this.$refs.updateClassesRef.openDialog();
    },
    del(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        this.$axios.get("/class/del?id=" + id).then(res => {
          if (res.data.code == 500) {
            this.$message(res.data.message)
            return
          }
          this.loadList();
        })

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
    }
  }

  ,
  data() {
    return {
      showList:[{}],
      dataList: [{
        name: "网络B171",
        desc: '活泼向上的班集体',
        people: '32',
        teacher: {
          name: '张三',
          tel: '13511111111',
        },
        course: [
          {courseName: 'xxxx', courseClasses: '王一峰', courseHours: 16}]
      }],
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
.headButton {
  margin: 10px auto;
}
</style>
