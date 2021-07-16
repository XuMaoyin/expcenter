<template>
  <el-dialog title="修改活动申请" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
    <el-form ref='addCourseForm' label-width="100px" v-model="form">


      <el-row>
        <el-col :span="12">
          <el-form-item label="活动地点">
            <el-select v-model="form.roomId" style="width: 150px">
              <el-option v-for="item in rooms" :key="item.id" :label="item.number" :value="item.id" style="width: 150px"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="活动人数">
            <el-input-number v-model="form.people" style="width: 150px"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动时间" >
            <el-date-picker v-model="form.date"  format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd" @change="changeDate" style="width: 150px"></el-date-picker>
            <span v-text="form.weak"></span>
          </el-form-item>
          <el-form-item label="节次" inline-message="">
            <el-select v-model="form.number" filterable placeholder="请选择" style="width: 150px">
              <el-option v-for="item in numbers" :key='item.vale' :value="item.vale" :label="item.label">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'重发申请'"></DialogButton>
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
    changeDate() {
      this.$axios.get("/config/getWeak?date=" + this.form.date).then(res => {
        this.form.weak = res.data.data;
      })
    },
    reset(){
      this.form={
        weak:'',
        activityName:'',
        details:'',
        roomId:'',
        datetime:'',
        people:0,
        number:'',
        leaderId:'',
        type:"teacher",
      }
    },
    handleClose() {
      this.dialogVisible = false;
    },
    sumbitForm() {
      this.dialogVisible = false;
    },
    openDialog() {
      this.$axios.get("/activity/activity?id="+this.activityId).then(res=>{
        this.form =res.data.data
      })

      this.dialogVisible = true
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          this.form.leaderId = JSON.parse(sessionStorage.getItem("user")).id;
          this.$axios.post("/activity/update",this.form).then(res=>{
            this.$message(res.data.message)
            if(res.data.code == 500){
              return
            }

            this.$parent.loadList();
            this.reset();
          })
          break
        case 'reset':
          this.reset();
          break
        case 'close':
          break
      }
      console.log(val)
      this.dialogVisible = false;
    }
  },
  mounted() {
    this.$axios.get("/room/rooms").then(res => {
      this.rooms = res.data.data;
    })

  },
  data() {
    return {
      dialogVisible: false,
      form: {
      },
      rooms:[],
      teachers:[],
      activityId:'',
      numbers: [
        {label:'1-2节',vale:1},
        {label:'3-4节',vale:2},
        {label:'5-6节',vale:3},
        {label:'7-8节',vale:4},
        {label:'9-10节',vale:5},
      ],
    }
  }
}
</script>

<style>
</style>
