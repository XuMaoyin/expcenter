<template>
	<el-dialog title="批量导入教师信息" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
		<el-form ref='addCourseForm' label-width="5px">

			<el-form-item>
				请先<a href="http://localhost:8002/teacher.xlsx">下载</a>模板，然后根据模板填入数据，否则会导入不成功
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
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="nickname" label="姓名"></el-table-column>
        <el-table-column prop="department" label="部门"></el-table-column>
        <el-table-column prop="tel" label="电话"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
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
            this.$axios.post("/teacher/import",this.toJson).then(res=>{
              this.$message(res.data.data);
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
