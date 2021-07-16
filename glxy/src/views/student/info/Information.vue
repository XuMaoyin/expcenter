<template>
  <el-form v-model="form" label-width="120px" style="width: 400px" v-if="!edit">
    <el-form-item label="社团名称">
      {{form.nickname}}
    </el-form-item>
    <el-form-item label="用户名">
      {{form.username}}
    </el-form-item>

    <el-form-item label="负责人姓名">
      {{form.leader}}
    </el-form-item>
    <el-form-item label="负责人联系方式">
      {{form.tel}}
    </el-form-item>
    <el-form-item label="负责人所在班级">
      {{form.classes}}
    </el-form-item>
    <el-form-item>
      <el-button @click="doChange" type="primary">修改</el-button>
    </el-form-item>
  </el-form>
  <el-form v-model="form" label-width="120px" style="width: 400px" v-else-if="edit">
    <el-form-item label="社团名称">
      <el-input v-model="form.nickname"></el-input>
    </el-form-item>
    <el-form-item label="用户名">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="新密码">
      <el-input v-model="form.newPassword"></el-input>
    </el-form-item>
    <el-form-item label="负责人姓名">
      <el-input v-model="form.leader"></el-input>

    </el-form-item>
    <el-form-item label="负责人联系方式">
      <el-input v-model="form.tel"></el-input>

    </el-form-item>
    <el-form-item label="负责人所在班级">
      <el-input v-model="form.classes"></el-input>

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
        id:'111222',
        nickName:"erp沙盘协会",
        username:'sssspp',
        password:'pppssss',
        newPassword:'',
        leader:'妥妥',
        tel:'13800000',
        classes:'工管'
      }
    }
  },
  methods:{
    doChange(){
      this.edit = !this.edit
    },
    saveChange(){
      this.doChange();
      this.form.password = this.form.password
      this.form.newPassword = '';
      this.$axios.post("club/update",this.form).then(res=>{
        this.$message(res.data.message)
        if(res.data.code == 200){
          //修改成功则同步修改本地数据
          sessionStorage.setItem("user",JSON.stringify(this.form));
        }
      })
    },
  },
  mounted() {
    var club = JSON.parse(sessionStorage.getItem("user"));
    console.log(club)
    this.form = club ;
  }
}
</script>

<style scoped>
.edit{
  border: 0px;
  outline:none;
}
</style>