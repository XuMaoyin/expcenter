<template>
  <div class="el-main">
    <el-form ref="applyCourseRef" :inline="true">
      <el-form-item label="活动搜索" >
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
      <el-table-column prop="status" label="状态" align="center">
        <template slot-scope="scope">
          <el-button type="text" v-if="scope.row.status == 0">待指导老师审核</el-button>
          <el-button type="text" v-if="scope.row.status == 1">待管理员审核</el-button>
          <el-button type="text" v-if="scope.row.status == 2">通过</el-button>
          <el-button type="text" v-if="scope.row.status == 3">不通过({{scope.row.reason}})</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template slot-scope="scope">

          <el-button size="small" v-if="scope.row.status == 2 || scope.row.status == 3" type="danger" @click="cancel(scope.row.id)">取消</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>

</template>

<script>
export default {
  name: 'courseAdmin',
  components: {

  },
  methods: {
    cancel(id){
      this.$axios.get("/apply/del?id="+id).then(res=>{
        this.$message(res.data.message)
      })
    },
    loadList(){
      this.$axios.get("/apply/club/applies?cid="+JSON.parse(sessionStorage.getItem("user")).id).then(res=>{
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
