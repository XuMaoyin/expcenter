<template>
  <el-dialog title="查看详情" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">

    <el-form ref='detailsRef' label-width="100px" v-model="form">

      <el-form-item label="课程名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select v-model="form.ids" multiple filterable placeholder="不修改请留空">
          <el-option v-for="item in classes" :key='item.id' :value="item.id" :label="item.className">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任课教师" filterable placeholder="请选择">
        <el-select v-model="form.leaderId">
          <el-option v-for="item in teachers" :key='item.id' :value="item.id" :label="item.nickname">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="实验次数">
        <el-input-number v-model="form.counts"></el-input-number>
      </el-form-item>
      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'修 改'"></DialogButton>
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
      this.$axios.get("/class/classes").then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message)
          return
        }

        this.classes = res.data.data;
      })

      this.$axios.get("/teacher/teachers").then(res => {
        this.teachers = res.data.data;
      })

      this.$axios.get("/course/course?id=" + this.courseId).then(res => {
        if (res.data.code == 500) {
          this.$message(res.data.message)
          return
        }
        /*失败的数组转换*/
       /* var ids = JSON.parse(res.data.data.classIds);
        console.log(ids)
        ids.map((value) => {
          console.log(value)
          let number = parseInt(value);
          console.log(number)
          return  number
        });
        console.log(ids);*/
        this.form = res.data.data;



      })

      this.dialogVisible = true
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          if( typeof(this.form.ids)!= "undefined" && this.form.ids!= null  ){
            var ids = this.form.ids.map(String)
            ids = JSON.stringify(ids);
            this.form.classIds = ids;
          }
          this.axios.post("/course/update", this.form).then(res => {
            if (res.data.code == 500) {
              this.$message(res.data.message)
              return
            }
            this.$parent.loadList();
            this.$refs.detailsRef.reduce()
            this.dialogVisible = false;

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
      form: {
        classIds: '',
        ids: [],
        name: '',
        leaderId: '',
        counts: 0,

      },
      classes: [],
      teachers: [],
      courseId: 0,
    }
  }
}
</script>

<style>
</style>
