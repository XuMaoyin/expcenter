<template>
  <div>

    <el-button size="small" @click="addClub" type="primary" >添加社团</el-button>

    <el-table :data="showList" height="510px">
      <el-table-column prop="nickname" label="名称"></el-table-column>
      <el-table-column prop="desc" label="描述"></el-table-column>
      <el-table-column prop="leader" label="负责人"></el-table-column>
      <el-table-column prop="classes" label="所在班级"></el-table-column>
      <el-table-column prop="tel" label="电话"></el-table-column>
      <el-table-column  label="操作">
        <template slot-scope="scope">
          <el-button size="small" @click="update(scope.row.id)">修改</el-button>
          <el-button type="danger" size="small" @click="delPlatForm(scope.row.id)" >删除</el-button>
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
      <add-club-dialog ref="addClubRef" ></add-club-dialog>
      <update-club-dialog ref="updateClubRef"></update-club-dialog>
    </div>

  </div>

</template>

<script>
import  AddClubDialog from './AddClubDialog.vue'
import  UpdateClubDialog from './UpdateClubDialog.vue'
export default {
  components:{
    AddClubDialog,UpdateClubDialog
  },
  data(){
    return {
      showList:[],
      dataList:[{}],
      page:{
        total:0,
        pageSize:8,
        currentPage:1,
      }
    }
  },
  methods:{
    addClub(){
      this.$refs.addClubRef.openDialog();
    },
    update(id){
      this.$refs.updateClubRef.clubId = id ;
      this.$refs.updateClubRef.openDialog();
    },
    loadList(){
      this.$axios.get("/club/clubs").then(res=>{
        if(res.data.code == 200){
          this.dataList = res.data.data
          this.page.total = this.dataList.length;
          //console.log(this.page.total)
          this.currentChange(1)
        }
        //this.$message(res.data.message);
      })
    },
    currentChange(val){
      const end = val * this.page.pageSize;
      const start = (val - 1) * this.page.pageSize;
      this.showList = this.dataList.slice(start,end);
    },
    delPlatForm(id){
      this.$axios.get("/club/del?id="+id).then(res=>{
        this.$message(res.data.message);
        this.loadList()
      })
    }
  },
  mounted() {
    this.loadList()
  }
}
</script>

<style scoped>

</style>