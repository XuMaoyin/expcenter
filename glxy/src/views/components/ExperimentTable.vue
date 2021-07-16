<template>
  <div>
    <el-form>
      <el-row>
        <el-col :span="8">
          <el-form-item label="课程名称">
            {{course.name}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="总实验次数">
            {{course.counts}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="未添加实验次数">
            {{course.counts-dataList.length}}
          </el-form-item>
        </el-col>

      </el-row>
      <el-form-item>
        <el-button @click='addSortHour' size="small" type="primary">添加实验</el-button>
        <el-button @click="changeEdit" v-text="edit?'完成编辑':'全部编辑'" size="small" type="primary"></el-button>

      </el-form-item>
    </el-form>

    <el-table :data="showList" height="470px">
      <el-table-column prop="name" label="实验名称"></el-table-column>
      <el-table-column prop="desc" label="实验内容"></el-table-column>
      <el-table-column label="实验室">
        <template slot-scope="scope">
          <span v-if="!scope.row.edit">{{ scope.row.classroom.number }}</span>
          <el-select size="mini" v-if="scope.row.edit" v-model="scope.row.roomId">
            <el-option v-for="item in rooms" :key="item.id" :label="item.number" :value="item.id"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="日期">
        <template slot-scope="scope">
          <span v-if="!scope.row.edit">{{ scope.row.date }}</span>
          <el-date-picker
              v-model="scope.row.date"
              v-if="scope.row.edit"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd" @change="changeDate(scope.row)"></el-date-picker>
          <br>
          <span>{{ scope.row.weak }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="number" label="节次">
        <template slot-scope="scope">
          <span v-if="!scope.row.edit">{{ scope.row.number }}</span>
          <el-select size="mini" v-if="scope.row.edit" v-model="scope.row.number">
            <el-option v-for="item in numbers" :key="item.value" :label="item.label" :value="item.vale"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button v-if="!scope.row.edit" type="" size="small" @click="scope.row.edit = true">编辑</el-button>
          <el-button v-if="scope.row.edit" size="small" @click="sumitEdit(scope.row)">完成</el-button>
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
    <div>
      <add-experiment-dialog ref="addRef"></add-experiment-dialog>
    </div>
  </div>

</template>

<script>
import AddExperimentDialog from './dialog/AddExperimentDialog.vue'

export default {
  components: {AddExperimentDialog,},
  data() {
    return {
      showList:[],
      dataList: [],
      edit: false,
      rooms: [],
      numbers: [
          {label:'1-2节',vale:1},
          {label:'3-4节',vale:2},
          {label:'5-6节',vale:3},
          {label:'7-8节',vale:4},
          {label:'9-10节',vale:5},
        ],
      course:'',
      page:{
        total:0,
        pageSize:7,
        currentPage:1,
      }
    }
  },
  methods: {
    changeDate(date){
      console.log(date.date)
      this.$axios.get("/config/getWeak?date="+date.date).then(res=>{
        date.weak = res.data.data;
      })
    },
    changeEdit() {

      //全局控制变量取反
      this.edit = !this.edit;

      //表中行数据取反
      if (this.edit) {

        for (let i = 0; i < this.dataList.length; i++) {
          this.dataList[i].edit = true;
        }
      } else {
        //修改编辑状态
        for (let i = 0; i < this.dataList.length; i++) {
          this.dataList[i].edit = false;
        }
        //保存数据到后端
        this.$axios.post("/experiment/change",this.dataList).then(res=>{
          this.$message(res.data.message)
        })
      }
    },
    sumitEdit(exp) {
      //单行完成编辑
      exp.edit = false;
      //提交单行内容并显示反馈
      this.$axios.post("/experiment/update",exp).then(res=>{
        this.$message(res.data.message)
      })
    },
    del(id) {
      //删除
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/experiment/del?id=" + id).then(res => {
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
    addSortHour() {
      this.$refs.addRef.openDialog();
    },
    loadList(){
      this.$axios.get("/experiment/experiments?courseId=" + this.$route.query.courseId).then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message)
          return
        }
        this.dataList = res.data.data;
        this.page.total = this.dataList.length;
        this.currentChange(1)
      })

      this.$axios.get("/course/course?id="+this.$route.query.courseId).then(res=>{
        this.course = res.data.data ;
      })
    },
    currentChange(val){
      const end = val * this.page.pageSize;
      const start = (val - 1) * this.page.pageSize;
      this.showList = this.dataList.slice(start,end);
    }

  },
  mounted() {

    this.loadList()
    this.$axios.get("/room/rooms").then(res=>{
      this.rooms = res.data.data;
    })


  }
}
</script>

<style>
</style>
