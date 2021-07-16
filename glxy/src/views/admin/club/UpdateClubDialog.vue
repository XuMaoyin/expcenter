<template>
  <el-dialog title="修改社团信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-form v-model="form" ref="addClubRef" label-width="100px">

      <el-form-item label="密码">
        <el-input size="small" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="负责人">
        <el-input size="small" v-model="form.leader"></el-input>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-input size="small" v-model="form.classes"></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input size="small" v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'确定修改'"></DialogButton>
      </el-form-item>
    </el-form>

  </el-dialog>
</template>

<script>
import DialogButton from '../../../components/DialogButton.vue'

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

      //获取初始数据
      this.$axios.get("/club/club?id="+this.clubId).then(res=>{
        this.form = res.data.data ;
      })

      this.dialogVisible = true;
    },

    reset(){
      this.form = {username:'',
        password:'',
        classes:'',
        leader:'',
        tel:'',}
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':

          this.$axios.post("/club/update", this.form).then(res => {
            if (res.data.code == 500) {
              this.$message(res.data.message)
              return
            }
            this.$parent.loadList();

          })

          this.reset();

          break
        case 'reset':
          break
        case 'close':
          break
      }
      console.log(val)
      this.dialogVisible = false;
    }
  },
  data() {
    return {
      dialogVisible: false,
      form: {
        password:'',
        classes:'',
        leader:'',
        tel:'',
      },

      clubId:'',
    }
  },
  mounted() {
  }
}
</script>

<style>
</style>
