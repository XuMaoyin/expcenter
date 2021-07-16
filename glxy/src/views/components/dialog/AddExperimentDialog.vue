<template>
  <el-dialog title="添加实验" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <el-form ref='addSortForm' label-width="100px" v-model="form">
      <el-form-item label="实验名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>

      <el-form-item label="描述">
        <el-input v-model="form.desc"></el-input>
      </el-form-item>

      <el-form-item label="日期">
        <el-date-picker
            v-model="form.date"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd" @change="changeDate"></el-date-picker>
        <span>{{ form.weak }}</span>
      </el-form-item>

      <el-form-item label="实验室">
        <el-select size="mini" v-model="form.roomId">
          <el-option v-for="item in rooms" :key="item.id" :label="item.number" :value="item.id"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="节次">
        <el-select size="mini" v-model="form.number">
          <el-option v-for="item in numbers" :key="item.value" :label="item.label" :value="item.vale"></el-option>
        </el-select>
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
      this.dialogVisible = true
    },
    reset(){
      this.form = {
        name: '',
        courseId: "",
        date: '',
        weak: '',
        roomId: '',
        number: '',
        desc: ''
      }
      this.form.courseId = this.$route.query.courseId;
    },
    diaglogBtn(val) {
      switch (val) {
        case 'sure':
          this.$axios.post("/experiment/add", this.form).then(res => {
            this.$message(res.data.message)

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
    },
    changeDate() {
      this.$axios.get("/config/getWeak?date=" + this.form.date).then(res => {
        this.form.weak = res.data.data;
      })
    },
  },
  data() {
    return {
      dialogVisible: false,
      form: {
        name: '',
        courseId: "",
        date: '',
        weak: '',
        roomId: '',
        number: '',
        desc: ''
      },
      numbers: [
        {label: '1-2节', vale: 1},
        {label: '3-4节', vale: 2},
        {label: '5-6节', vale: 3},
        {label: '7-8节', vale: 4},
        {label: '9-10节', vale: 5},
      ],
      rooms: [],
    }
  },
  mounted() {
    this.form.courseId = this.$route.query.courseId;
    //console.log(this.form.courseId)

    this.$axios.get("/room/rooms").then(res => {
      this.rooms = res.data.data;
    })
  }

}
</script>

<style>
</style>
