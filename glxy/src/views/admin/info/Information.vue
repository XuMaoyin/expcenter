<template>
  <div style="margin: 0 auto;">
    <el-form v-model="form1" ref="infoFormRef" label-width="150px" style="width: 800px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="课程调整">
            <el-switch v-model="form1.course" active-value="1" inactive-value="0"
                       @change="doChange($event,'course')"></el-switch>
          </el-form-item>
          <el-form-item label="实验调整">
            <el-switch v-model="form1.experiment" active-value="1" inactive-value="0"
                       @change="doChange($event,'experiment')"></el-switch>
          </el-form-item>
          <el-form-item label="活动申请">
            <el-switch v-model="form1.activity" active-value="1" inactive-value="0"
                       @change="doChange($event,'activity')"></el-switch>
          </el-form-item>
          <el-form-item label="自动审核（整体）">
            <el-switch v-model="form1.auto_all" active-value="1" inactive-value="0"
                       @change="doChange($event,'auto_all')"></el-switch>
          </el-form-item>
          <el-form-item label="自动审核（课程）">
            <el-switch v-model="form1.auto_course" active-value="1" inactive-value="0"
                       @change="doChange($event,'auto_course')"></el-switch>
          </el-form-item>
          <el-form-item label="自动审核（实验）">
            <el-switch v-model="form1.auto_experiment" active-value="1" inactive-value="0"
                       @change="doChange($event,'auto_experiment')"></el-switch>
          </el-form-item>
          <el-form-item label="自动审核（活动）">
            <el-switch v-model="form1.auto_activity" active-value="1" inactive-value="0"
                       @change="doChange($event,'auto_activity')"></el-switch>
          </el-form-item>
          <el-form-item label="学期起始日期">
            <el-date-picker
                v-model="form1.startsTime"
                @change="doChange($event,'startsTime')"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                style="width: 150px;"
                :clearable="false"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item>
          </el-form-item>
          <el-form-item label="旧密码">
            <el-input type="password" v-model="form2.oldPassword" placeholder="请输入6位以上的密码"></el-input>
          </el-form-item>
          <el-form-item label="新密码">
            <el-input type="password" v-model="form2.newPassword" placeholder="请输入6位以上的密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="changePassword">修改密码</el-button>
          </el-form-item>
        </el-col>
      </el-row>


    </el-form>
  </div>
</template>

<script>
export default {
  name: "Information",
  data() {
    return {
      form1: {
        "password": "123456",
        "auto_experiment": "1",
        "activity": "1",
        "experiment": "1",
        "auto_all": "1",
        "course": "1",
        "auto_course": "1",
        "auto_activity": "1",
        startsTime: '2021-1-1'
      },
      form2: {
        oldPassword: '',
        newPassword: '',
      }
    }
  },
  methods: {
    doChange(event, key) {

      //event为点击后switch组件的值
      this.$axios.post("/config/update", {key: key, value: event}).then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message)
        }
        console.log(res.data)
      })

    },

    changePassword() {
      //验证
      if (this.form2.oldPassword == '' || this.form2.newPassword == '') {
        this.$alert("请输入")
        return
      }
      if (this.form2.oldPassword != this.form1.password) {
        this.$alert("旧密码不匹配")
        return
      }

      //发送请求
      this.$axios.post("/config/update", {key: 'password', value: this.form2.newPassword}).then(res => {
        //显示后台反馈
        this.$message(res.data.message)
        if (res.data.code == 200) {
          //成功则更新密码信息
          this.form1.password = this.form2.newPassword;

          //表单清空
          this.form2.oldPassword = '';
          this.form2.newPassword = '';
        }
      })

    }
  },
  mounted() {
    this.$axios.get("/config/configs").then(res => {
      this.form1 = res.data.data;
      //console.log(res.data);
    })

  }
}
</script>

<style scoped>

</style>