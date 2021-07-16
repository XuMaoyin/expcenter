<template>

  <div class="el-main">
    <el-form ref="applyCourseRef" :inline="true">
      <el-form-item>
        <el-button size="small" type="primary" @click='addActivity'>添加活动记录</el-button>
      </el-form-item>

      <el-form-item label="活动搜索" class="inlineform">
        <el-input v-model="activityName" size="small" placeholder="请输入活动名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="showList" style="width: 100%;" height="470px">

      <el-table-column prop="activityName" label="活动名称"></el-table-column>
      <el-table-column prop="date" label="活动时间"></el-table-column>
      <el-table-column prop="classroom.number" label="活动地点"></el-table-column>
      <el-table-column prop="number" label="节次"></el-table-column>
      <el-table-column prop="details" label="活动内容"></el-table-column>
      <el-table-column prop="people" label="人数"></el-table-column>
      <el-table-column prop="club.nickname" label="社团/组织"></el-table-column>
      <el-table-column prop="club.leader" label="负责人"></el-table-column>
      <el-table-column prop="club.tel" label="联系方式"></el-table-column>
      <el-table-column label="指导教师">
        <template slot-scope="scope">
          <el-popover trigger="click" placement="top">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.teacher.nickname }}</el-tag>
            </div>
            <el-form>
              <el-form-item label="联系方式">
                {{ scope.row.teacher.tel }}
              </el-form-item>
              <el-form-item label="所属部门">
                {{ scope.row.teacher.department }}
              </el-form-item>
            </el-form>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <el-button type="text" v-if="scope.row.status == 0">待申请</el-button>
          <el-button type="text" v-if="scope.row.status == 1">待审核</el-button>
          <el-button type="text" v-if="scope.row.status == 2">审核通过</el-button>
          <el-button type="text" v-if="scope.row.status == 3">审核不通过</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
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
      <add-student-activity-dialog ref="addStudentRef"></add-student-activity-dialog>
    </div>

  </div>

</template>

<script>
import addStudentActivityDialog from './dialog/AddStudentActivityDialog.vue'

export default {
  name: 'courseAdmin',
  components: {
    addStudentActivityDialog
  },
  methods: {
    search(){
      this.$axios.get("/activity/search?type=club&activityName="+this.activityName).then(res=>{
        this.dataList = res.data.data;
        this.page.total = this.dataList.length;
        this.currentChange(1)
      })
    },
    addActivity() {
      //console.log('nihao')
      this.$refs.addStudentRef.openDialog();
    },
    del(id) {
      console.log('del')
      this.$alert('确定要删除该活动吗', '标题名称', {
        confirmButtonText: '确定',
        callback: action => {
          this.$axios.get("/activity/del?id="+id).then(res=>{
            this.$message(res.data.message);

            if(res.data.code == 200){
              this.loadList()
            }

          })
        }
      });
    },
    loadList(){
      this.$axios.get("/activity/activities?type=club").then(res=>{
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
    this.loadList()
  },
  data() {
    return {
      showList:[],
      dataList: [],
      activityName: '',
      page:{
        total:0,
        pageSize:6,
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

.inlineform {
  margin-left: 100px;
}
</style>
