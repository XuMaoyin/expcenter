<template>
  <el-form v-model="form" label-width="120px" style="width: 400px" v-if="!edit">
    <el-form-item label="用户名">
      {{form.username}}
    </el-form-item>
    <el-form-item label="姓名">
      {{form.nickname}}
    </el-form-item>
    <el-form-item label="性别">
      {{form.sex}}
    </el-form-item>
    <el-form-item label="生日">
      {{form.birthday}}
    </el-form-item>
    <el-form-item label="个签">
      {{form.desc}}
    </el-form-item>
    <el-form-item label="邮箱">
      {{form.email}}
    </el-form-item>
    <el-form-item label="电话">
      {{form.tel}}
    </el-form-item>
    <el-form-item label="部门">
      {{form.department}}
    </el-form-item>
    <el-form-item>
      <el-button @click="doChange" type="primary">修改</el-button>
    </el-form-item>
  </el-form>
  <el-form v-model="form" label-width="120px" style="width: 400px" v-else-if="edit">
    <el-form-item label="用户名">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="新密码">
      <el-input v-model="form.newPassword"></el-input>
    </el-form-item>
    <el-form-item label="姓名">
      <el-input v-model="form.nickname"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-input v-model="form.sex"></el-input>
    </el-form-item>
    <el-form-item label="生日">
      <el-input v-model="form.birthday"></el-input>
    </el-form-item>
    <el-form-item label="个签">
      <el-input v-model="form.desc"></el-input>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="电话">
      <el-input v-model="form.tel"></el-input>
    </el-form-item>
    <el-form-item label="部门">
      <el-input v-model="form.department"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button @click="saveChange" type="success">保存</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Information",
  data(){
    return {
      edit:false,
      form:{

      }
    }
  },
  methods:{
    doChange(){
      this.edit = !this.edit
    },
    saveChange(){
      this.doChange();
      if(this.form.newPassword != ''){
        this.form.password = this.form.newPassword
        this.form.newPassword = '';
      }
      this.axios.post("/teacher/updateInfo",this.form).then(res=>{
          if(res.data.code == 200){
            this.$message(res.data.message)
          }else{
            this.form = res.data.data;
           // console.log(res.data)
          }

      })
    }
  },
  mounted() {
    this.form = JSON.parse( sessionStorage.getItem("user"));
    this.newPassword = '';
    //console.log(this.form)
  }
}
</script>

<style scoped>
.edit{
  border: 0px;
  outline:none;
}
</style>