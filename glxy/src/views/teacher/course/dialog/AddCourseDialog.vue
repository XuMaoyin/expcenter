<template>
  <el-dialog title="添加课程" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-form ref='addCourseForm' label-width="100px" v-model="form">

      <el-form-item label="课程名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select v-model="form.ids" multiple filterable placeholder="请选择" @change="select">
          <el-option v-for="item in classes" :key='item.id' :value="item.id" :label="item.className">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="实验次数">
        <el-input-number v-model="form.counts"></el-input-number>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'添 加'"></DialogButton>
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
    select(res) {
      //
    },

    handleClose() {
      this.dialogVisible = false;
    },
    sumbitForm() {
      this.dialogVisible = false;
    },
    openDialog() {
      this.dialogVisible = true
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          var ids = this.form.ids.map(String)
          ids = JSON.stringify(ids);
          this.form.classIds = ids;
          this.axios.post("/course/add",this.form).then(res=>{
            if(res.data.code == 500){
              this.$message(res.data.message)
              return
            }
            this.$parent.loadList();
            this.$refs.addCourseForm.reduce()
            this.dialogVisible = false ;

          })
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
  props: {},

  data() {
    return {
      dialogVisible: false,

      form:{
        classIds:'',
        ids : [],
        name:'',
        leaderId:'',
        counts:0,

      },
      classes: [],


    }
  },
  mounted() {
    this.$axios.get("/class/classes").then(res => {
      if (res.data.code == 500) {
        this.$message(res.data.message)
        return
      }

      this.classes = res.data.data;
    })

  }

}
</script>

<style>
</style>
