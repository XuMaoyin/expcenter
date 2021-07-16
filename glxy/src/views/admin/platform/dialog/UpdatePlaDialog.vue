<template>
  <el-dialog title="" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
    <el-form ref='updateFormRef' label-width="100px" >

      <el-form-item label="平台名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.url"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="form.details"></el-input>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :reset="true" :sure="true" :sureName="'修 改'"></DialogButton>
      </el-form-item>

    </el-form>
  </el-dialog>
</template>

<script>
import DialogButton from '../../../../components/DialogButton.vue'

export default {
  components: {
    DialogButton,

  },
  computed: {

  },
  methods: {

    reset(){
      this.form.title = '';
      this.form.content = '';
    },
    handleClose() {
      this.dialogVisible = false;
    },
    sumbitForm() {
      this.dialogVisible = false;
    },
    openDialog() {
      this.$axios.get("/platform/platform?id="+this.platformId).then(res=>{
        if(res.data.code == 500){
          this.$message(res.data.message)
          return
        }
        this.form = res.data.data ;
      })
      this.dialogVisible = true
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          this.$axios.post("/platform/update",this.form).then(res=> {
            if (res.data.code == 500) {
              this.$message(res.data.message)
              return
            }
            this.reset()
            this.$parent.loadList();
          })
          break
        case 'reset':
          this.reset()
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
      form:{

      },
      platformId:0,

    }
  }
}
</script>

<style scoped>

</style>
