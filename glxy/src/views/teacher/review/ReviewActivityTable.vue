<template>
  <div>
    <el-form ref="applyCourseRef" :inline="true">
      <el-form-item label="活动搜索">
        <el-input v-model="activityName" size="small" placeholder="请输入活动名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="dataList" style="width: 100%;" :border="false">

      <el-table-column prop="date" label="申请时间"></el-table-column>
      <el-table-column prop="activity.activityName" label="活动名称"></el-table-column>
      <el-table-column prop="activity.date" label="活动日期"></el-table-column>
      <el-table-column prop="activity.number" label="节次"></el-table-column>
      <el-table-column prop="activity.details" label="活动内容"></el-table-column>
      <el-table-column prop="activity.classroom.number" label="活动地点"></el-table-column>
      <el-table-column prop="activity.people" label="人数"></el-table-column>
      <el-table-column prop="activity.club.nickname" label="社团名称"></el-table-column>
      <el-table-column prop="activity.club.leader" label="负责人"></el-table-column>
      <el-table-column prop="activity.club.tel" label="电话"></el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope" v-if="scope.row.status == 0">
          <el-button size="small" type="danger" @click="pass(scope.row.id)">通过</el-button>
          <el-button size="small" type="danger" @click="review(scope.row.id)">拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>

</template>

<script>
export default {
  name: 'courseAdmin',
  components: {},
  methods: {
    review(id){

      this.$prompt('请输入拒绝理由', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$axios.get("/apply/teacher/unpass?id="+id+'&reason='+value).then(res=>{
          this.$message(res.data.message);
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });


    },
    pass(id){
      this.$axios.get("/apply/teacher/pass?id="+id).then(res=>{
        this.$message(res.data.message);
      })
    },


    loadList() {
      this.$axios.get("/apply/teacher/club/applies?tid=" + JSON.parse(sessionStorage.getItem("user")).id).then(res => {
        this.dataList = res.data.data;
      })
    },
  },
  mounted() {
    this.loadList()
  }

  ,
  data() {
    return {
      dataList: [{}, {
        type: 'teacher',
        status: '审核中',
      }],
      auto: false,
      type: '',
      activityName: '',
    }
  }
}
</script>

<style>
.headButton {
  margin: 10px auto;
}


</style>


