<template>
  <el-dialog title="添加社团" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-form v-model="form" ref="addClubRef" label-width="100px">
      <el-form-item label="名称">
        <el-input size="small" v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input size="small" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="负责人">
        <el-input size="small" v-model="form.leader"></el-input>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select v-model="form.classes">
          <el-option v-for="item in classes" :key="item.id" :value="item.className" :label="item.className"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input size="small" v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'添 加'"></DialogButton>
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
      this.dialogVisible = true;
    },

    reset(){
      this.form = {
        password:'123456',
        nickname: '',
        desc: '',
        classes:'',
        leader:'',
        tel:'',}
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':

          this.$axios.post("/club/add", this.form).then(res => {
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
        password:'123456',
        nickname: '',
        desc: '',
        classes:'',
        leader:'',
        tel:'',
      },
      classes:[],
    }
  },
  mounted() {
    this.$axios.get("/class/classes").then(res=>{
      this.classes = res.data.data;
    })
  }
}
</script>

<style>
</style>
