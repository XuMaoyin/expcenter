<template>
  <div>

    <el-form inline v-model="form">
      <el-form-item label="新增实验平台：">

      </el-form-item>
      <el-form-item label="名称">
        <el-input size="small" v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input size="small" v-model="form.url"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input size="small" v-model="form.details"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="addPlatForm">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="showList">
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="details" label="描述"></el-table-column>
      <el-table-column prop="url" label="地址"></el-table-column>
      <el-table-column  label="操作">
        <template slot-scope="scope">
          <el-button size="small" @click="update(scope.row.id)" >修改</el-button>
          <el-button type="danger" size="small" @click="delPlatForm(scope.row.id)" >删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <div style="text-align: center;margin-top: 10px">
      <el-pagination
          background
          @current-change="currentChange"
          layout="prev, pager, next"
          :total="page.total">
      </el-pagination>
    </div>

    <div>
      <update-pla-dialog ref="updatePlaRef"></update-pla-dialog>
    </div>


  </div>

</template>

<script>
import UpdatePlaDialog from "./dialog/UpdatePlaDialog.vue"
export default {
  name: "PlatformTable",
  components:{UpdatePlaDialog},
  data(){
    return {
      showList:[],
      dataList:[{}],
      form:{
        name:'',
        details:'',
        url:''
      },
      page:{
        total:0,
        pageSize:8,
        currentPage:1,
      }
    }
  },
  methods:{
    update(id){
      //console.log(id)
      this.$refs.updatePlaRef.platformId = id;
      this.$refs.updatePlaRef.openDialog();
    }
    ,
    loadList(){
      this.$axios.get("/platform/platforms").then(res=>{
          this.dataList = res.data.data
        this.page.total = this.dataList.length
        this.currentChange(1);
      })
    },
    currentChange(val){
      const end = val * this.page.pageSize;
      const start = (val - 1) * this.page.pageSize;
      this.showList = this.dataList.slice(start,end);
    },
    addPlatForm(){
      this.$axios.post("/platform/add",this.form).then(res=>{
        this.$message(res.data.message);
        this.loadList()
        if(res.data.code == 200){
          this.form = {
            name:'',
            details:'',
            url:''
          }
        }
      })
    },
    delPlatForm(id){
      this.$axios.get("/platform/del?id="+id).then(res=>{
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