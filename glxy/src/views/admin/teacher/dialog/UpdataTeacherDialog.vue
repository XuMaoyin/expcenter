<template>
  <el-dialog title="修改信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-form ref='updateTeacherFormRef' label-width="100px" v-model="form">

      <el-form-item label="姓名">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>
      <el-form-item label="部门">
        <el-select v-model="form.department" filterable placeholder="请选择">
          <el-option v-for="item in departments" :key='item.label' :value="item.value" :label="item.label">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email"></el-input>
      </el-form-item>

      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>

      <el-form-item label="密码">
        <el-input type="password" v-model="form.password"></el-input>
      </el-form-item>

      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'立即修改'"></DialogButton>
      </el-form-item>


    </el-form>
  </el-dialog>
</template>

<script>
import DialogButton from '../../../../components/DialogButton.vue'

export default {
  components: {
    DialogButton
  },
  methods: {
    handleClose() {
      this.dialogVisible = false;
    },
    sumbitForm() {
      this.dialogVisible = false;
    },
    openDialog() {
      //console.log(this.teacherId)
      //获取当前教师信息
      this.$axios.get("/teacher/teacher?id=" + this.teacherId).then(res => {
        this.form = res.data.data;
      })
      this.dialogVisible = true
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          this.axios.post("/teacher/update", this.form).then(res => {
            if (res.data.code == 500) {
              this.$message(res.data.message);
              return
            }
          })
          this.$parent.loadList();
          this.$refs.updateTeacherFormRef.resetFields();

          break
        case 'reset':
          this.$axios.get("/teacher/teacher?id=" + this.teacherId).then(res => {
            this.form = res.data.data;
          })
          break
        case 'close':
          break
      }
      //console.log(val)
      this.dialogVisible = false;
    }
  },
  data() {
    return {
      form: {
        nickname: '',
        sex: '',
        department: '',
        tel: '',
        email: '',
        username: '',
        password: '',
      },
      teacherId: 0,
      dialogVisible: false,
      departments: [{
        label: '1',
        value: '1'
      },
        {
          label: '2',
          value: '2'
        },
        {
          label: '3',
          value: '3'
        },
        {
          label: '4',
          value: '4'
        },
      ],
    }
  }
}
</script>

<style>
</style>
