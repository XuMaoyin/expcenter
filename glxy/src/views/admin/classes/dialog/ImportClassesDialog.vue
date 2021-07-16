<template>
  <el-dialog title="批量导入班级信息" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
    <el-form ref='addCourseForm' label-width="5px">

      <el-form-item>
        请先<a href="http://localhost:8002/classes.xlsx">下载</a>模板，然后根据模板填入数据，否则会导入不成功
        <a class="el-button" style="position: relative;width: 130px;margin-left: 30px">选择文件
          <input
              type="file"
              ref="upload"
              accept=".xls, .xlsx"
              class="el-button el-button--primary"
              style="opacity: 0;position:absolute;top: 0px;left: 0px;width: 130px"
              @change="readExcel"
          />
        </a>
      </el-form-item>
      <el-table :data="toJson">
        <el-table-column prop="className" label="班级名称"></el-table-column>
        <el-table-column prop="people" label="人数"></el-table-column>
        <el-table-column prop="leader" label="班主任"></el-table-column>
      </el-table>

      <el-form-item>
        <DialogButton @diaglogBtn="diaglogBtn" :sure="true" :sureName="'导 入'"></DialogButton>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import DialogButton from '../../../../components/DialogButton.vue'
import XLSX from 'xlsx'
export default {
  components: {
    DialogButton
  },
  methods: {
    readExcel(e){
      //console.log(e)
      //表格导入
      const files = e.target.files;
      const fileReader = new FileReader();
      fileReader.onload = ev => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: "binary"
          });
          const wsname = workbook.SheetNames[0]; //取第一张表
          //console.log(workbook)
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
          this.toJson = ws// 转化成功的json
          console.log(this.toJson);

        } catch (e) {
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);
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
          this.$axios.post("/class/import",this.toJson).then(res=>{
            this.$message(res.data.message);
            if(res.data.code == 200)
              this.$parent.reload()
          })
          break
        case 'reset':
          break
        case 'close':
          break
      }
      console.log(val)
      this.dialogVisible = false;
    },
  },
  props: {

  },
  data() {
    return {
      dialogVisible: false,
      toJson:[{}],
    }
  }
}
</script>

<style>
</style>
